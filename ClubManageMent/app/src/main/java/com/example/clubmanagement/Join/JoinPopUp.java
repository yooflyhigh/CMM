package com.example.clubmanagement.Join;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.clubmanagement.ClubPage.Home.ClubPoster;
import com.example.clubmanagement.DataBase.DBConnect.CNT_JoinRequest;
import com.example.clubmanagement.Profile.UserID;
import com.example.clubmanagement.R;

import static java.lang.Thread.sleep;

public class JoinPopUp extends Activity implements View.OnClickListener {
    Button join; // 버튼 선언
    Button cancel;
    LinearLayout linear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_popup);

        linear = (LinearLayout) findViewById(R.id.linr);
        linear.setBackground(ClubPoster.image);

        join = (Button) findViewById(R.id.join);
        join.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    /* 가입 or 취소 버튼 클릭 시 */
    public void onClick(View view) {
        if (view == join) {
            CNT_JoinRequest cn = new CNT_JoinRequest();
            Intent intent = getIntent();
            String CLUB_ID = intent.getExtras().getString("CLUB_ID");
            String result = cn.JoinRequest(CLUB_ID, UserID.UserID);

            if(result.equals("-1")){
                new AlertDialog.Builder(this)
                        .setTitle("가입실패")
                        .setMessage("이미 가입된 동아리 입니다.")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show(); // 팝업창 보여줌
            }
            else{
                new AlertDialog.Builder(this)
                        .setTitle("가입확인")
                        .setMessage("가입신청 되었습니다.")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show(); // 팝업창 보여줌
            }
        } else if (view == cancel) {
            finish();
        }
    }

    /* 바깥레이어 클릭시 안닫히게 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    /* 뒤로가기 버튼 눌렀을 때 */
    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    /*
    public void mJoin(View v) {
        //데이터 전달하기
        Intent intent = new Intent(this, Application_Form.class);
        startActivity(intent);
        //intent.putExtra("result", "Close Popup");
        //setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    public void mBack(View v) {
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }
    */
}