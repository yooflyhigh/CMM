package com.example.clubmanagement.System.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.clubmanagement.Join.Center;
import com.example.clubmanagement.Login.Auto_Login.SaveSharedPreference;
import com.example.clubmanagement.Login.Login_View.LoginActivity;
import com.example.clubmanagement.System.Adapter.FragmentAdapter.Fragment_Page_Adapter;
import com.example.clubmanagement.R;
import com.example.clubmanagement.System.Listener.BackPressCloseHandler;

import java.util.List;

public class Fragment_Start extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BackPressCloseHandler BackPressCloseHandler = new BackPressCloseHandler(this);
    Toolbar toolbar;
    Button Btn_logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_frgment_start);

        setToolbar();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment_Page_Adapter mFrgmntPageAdapter = new Fragment_Page_Adapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mFrgmntPageAdapter);
        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
    }

    /* 네비게이션 부분 */
    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.account:
//                Snackbar.make(findViewById(R.id.account),"Navigation Account pressed",Snackbar.LENGTH_SHORT).show();
                Log.d("위에서 클릭"," 클릭됨");
                break;
            case R.id.setting:
                Log.d("setting 클릭"," 클릭됨");
                Snackbar.make(findViewById(R.id.setting),"Navigation Setting pressed",Snackbar.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.closeDrawers();

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*
        switch(item.getItemId()) {
            case R.id.account:
                //Snackbar.make(toolbar, (CharSequence)"account click", -1).show();
                 Log.d("어카운트 클릭"," 클릭됨");
                break;
            //case -1000132:
            //   Snackbar.make(toolbar, (CharSequence)"Logout menu pressed", -1).show();
            // break;
            case -1000091:
                Snackbar.make(toolbar, (CharSequence)"Search menu pressed", -1).show();
                break;
            case -1000055:
                Snackbar.make(toolbar, (CharSequence)"Account menu pressed", -1).show();
                break;
            case android.R.id.home:
                ((DrawerLayout)this.findViewById(R.id.drawerLayout)).openDrawer(Gravity.START);
                break;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /* 옵션 부분 */
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.findItem(R.drawable.logout);
        //getMenuInflater().inflate(R.menu.navi_item, menu);
        return true;
    }
    */

    /* 로그아웃 버튼 */
    public void onLogoutButtonClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("로그아웃");
        alertDialogBuilder
                .setMessage("로그아웃 하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 프로그램을 종료한다
                                SaveSharedPreference.setUserName(Fragment_Start.this, "");
                                SaveSharedPreference.setUserPass(Fragment_Start.this, "");
                                SaveSharedPreference.setAutoLoginCheck(Fragment_Start.this,false);
                                startActivity(new Intent(Fragment_Start.this, LoginActivity.class));
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /* 프레그먼트 백버튼 리스너 */
    public interface OnBackPressedListener {
        void onBackPressed();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            BackPressCloseHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료", 3000);

            for(Fragment fragment : fragmentList){
                if(fragment instanceof OnBackPressedListener){
                    ((OnBackPressedListener)fragment).onBackPressed();
                }
            }
        }
    }
}