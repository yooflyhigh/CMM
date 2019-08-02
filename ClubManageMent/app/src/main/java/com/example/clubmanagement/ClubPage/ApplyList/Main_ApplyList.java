package com.example.clubmanagement.ClubPage.ApplyList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clubmanagement.DataBase.DBConnect.CNT_JoinResponse;
import com.example.clubmanagement.DataBase.DBConnect.CNT_JoinResponseDelete;
import com.example.clubmanagement.DataBase.DBRefresh;
import com.example.clubmanagement.System.Adapter.ListViewAdapter.ApplyList_ListAdapter;
import com.example.clubmanagement.R;
import com.example.clubmanagement.System.ListVO.ListVO_Apply;

import java.util.HashMap;

import static com.example.clubmanagement.DataBase.DataPool.Member.Club_Member_Item_list;
import static java.lang.Thread.sleep;

public class Main_ApplyList extends AppCompatActivity implements View.OnClickListener{
    private ListView listview ;
    private ApplyList_ListAdapter adapter;
    private int[] Apply_Img= {R.drawable.box, R.drawable.x,R.drawable.check};

    int Pos = Integer.MAX_VALUE;
    Button Getout;
    Button Deny;
    Button Accept;
    private String CLUB_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applylist_main);
        Intent intent = getIntent();
        CLUB_ID = intent.getExtras().getString("CLUB_ID");

        try {
            DBRefresh.Refresh();
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ApplyList_ListAdapter();
        listview = (ListView) findViewById(R.id.Apply_ListView);
        listview.setAdapter(adapter);

        ShowList();
        Getout = (Button)findViewById(R.id.Getout) ;
        Getout.setOnClickListener(this);
        Deny = (Button)findViewById(R.id.deny);
        Deny.setOnClickListener(this);
        Accept = (Button) findViewById(R.id.accept);
        Accept.setOnClickListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pos = position;
            }
        });

    }

    private void ShowList() {
        HashMap<String, String> Member;
        for(int i=0; i<Club_Member_Item_list.size();i++){
            Member = Club_Member_Item_list.get(i);
            if (Member.get("CLUB_ID").equals(CLUB_ID) && Member.get("JOIN_CD").equals("2")) {
                adapter.addVO(ContextCompat.getDrawable(this,Apply_Img[0]), Member.get("MAJOR"),Member.get("STUDENT_ID"),Member.get("GRADE"),Member.get("NM"),Member.get("GENDER_CD"),Member.get("PHONE_NO"));

            }
            if (Member.get("CLUB_ID").equals(CLUB_ID) && Member.get("JOIN_CD").equals("3")) {
                adapter.addVO(ContextCompat.getDrawable(this,Apply_Img[1]), Member.get("MAJOR"),Member.get("STUDENT_ID"),Member.get("GRADE"),Member.get("NM"),Member.get("GENDER_CD"),Member.get("PHONE_NO"));
            }
        }
    }

    @Override
    public void onClick(View v) {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pos = position;
            }
        });
        if(Pos < listview.getCount()) {
            if(v == Getout){
                final ListVO_Apply SelectedID = (ListVO_Apply)(listview.getAdapter().getItem(Pos));
                CNT_JoinResponseDelete cn = new CNT_JoinResponseDelete();
                cn.CNT_JoinResponse(CLUB_ID, SelectedID.getSTUDENT_ID(), SelectedID.getNM());

                new AlertDialog.Builder(Main_ApplyList.this)
                        .setTitle("퇴출확인")
                        .setMessage("퇴출 되었습니다.")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SelectedID.setApply_Img(ContextCompat.getDrawable(Main_ApplyList.this,Apply_Img[1]));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
            else if(v == Deny) {
                final ListVO_Apply SelectedID = (ListVO_Apply)(listview.getAdapter().getItem(Pos));
                CNT_JoinResponse cn = new CNT_JoinResponse();
                cn.CNT_JoinResponse(CLUB_ID, SelectedID.getSTUDENT_ID(), "2");

                new AlertDialog.Builder(Main_ApplyList.this)
                        .setTitle("거절확인")
                        .setMessage("거절 되었습니다.")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SelectedID.setApply_Img(ContextCompat.getDrawable(Main_ApplyList.this,Apply_Img[0]));
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .show();
            }

            else if(v == Accept){
                final ListVO_Apply SelectedID = (ListVO_Apply)(listview.getAdapter().getItem(Pos));
                CNT_JoinResponse cn = new CNT_JoinResponse();
                cn.CNT_JoinResponse(CLUB_ID, SelectedID.getSTUDENT_ID(), "1");

                new AlertDialog.Builder(Main_ApplyList.this)
                        .setTitle("가입확인")
                        .setMessage("승인 되었습니다.")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SelectedID.setApply_Img(ContextCompat.getDrawable(Main_ApplyList.this,Apply_Img[2]));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        }
        else{
            Toast.makeText(this,"대상을 선택하세요.", Toast.LENGTH_SHORT).show();
        }

    }
}

