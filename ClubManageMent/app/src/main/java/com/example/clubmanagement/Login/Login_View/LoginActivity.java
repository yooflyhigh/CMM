package com.example.clubmanagement.Login.Login_View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clubmanagement.System.Fragment.Fragment_Start;
import com.example.clubmanagement.Login.Auto_Login.SaveSharedPreference;
import com.example.clubmanagement.R;
import com.example.clubmanagement.System.Listener.BackPressCloseHandler;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private BackPressCloseHandler BackPressCloseHandler = new BackPressCloseHandler(this);

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);

            loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                    .get(LoginViewModel.class);

            final EditText usernameEditText = findViewById(R.id.username);
            final EditText passwordEditText = findViewById(R.id.password);
            final Button loginButton = findViewById(R.id.login);
            final ProgressBar loadingProgressBar = findViewById(R.id.loading);
            final CheckBox autoLogin = findViewById(R.id.autoLogin);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    loadingProgressBar.setVisibility(View.VISIBLE);
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
            });

            if(SaveSharedPreference.getIsEnabled(LoginActivity.this)) {
                usernameEditText.setText(SaveSharedPreference.getUserName(LoginActivity.this));
                passwordEditText.setText(SaveSharedPreference.getUserPass(LoginActivity.this));
                autoLogin.setChecked(true);
                loginButton.callOnClick();
    }

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());

                    if(autoLogin.isChecked()){
                        SaveSharedPreference.setUserName(LoginActivity.this, usernameEditText.getText().toString());
                        SaveSharedPreference.setUserPass(LoginActivity.this, passwordEditText.getText().toString());
                        SaveSharedPreference.setAutoLoginCheck(LoginActivity.this,true);
                    }
                    else{
                        SaveSharedPreference.setUserName(LoginActivity.this, "");
                        SaveSharedPreference.setUserPass(LoginActivity.this, "");
                        SaveSharedPreference.setAutoLoginCheck(LoginActivity.this,false);
                    }

                    startActivity(new Intent(LoginActivity.this, Fragment_Start.class));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }
            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = model.getDisplayName() + " 님이 로그인 하셨습니다.";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        BackPressCloseHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);
    }
}
