package com.example.clubmanagement.ClubPage.MemberList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.clubmanagement.System.Adapter.ListViewAdapter.Member_ListAdapter;
import com.example.clubmanagement.R;

import static java.lang.Thread.sleep;

public class Main_MemberList extends AppCompatActivity {
    private ListView listview;
    private Member_ListAdapter adapter;
    private String[] staff = {"회장", "부회장", "총무", "회원", "회원", "회원", "회원", "회원", "회원", "회원", "회원"};
    private String[] name = {"정균모", "이호명", "이지아", "김중형", "김중형", "김중형", "김중형", "김중형", "김중형", "김중형", "김중형"};
    private String[] major = {"빅데이터", "콘텐츠IT", "빅데이터", "콘텐츠IT", "빅데이터", "콘텐츠IT", "빅데이터", "콘텐츠IT", "빅데이터", "콘텐츠IT", "빅데이터"};
    private String[] phoneNM = {"010-5253-4567", "010-5352-1523", "010-3525-5232", "010-9424-5525", "010-9424-5525", "010-9424-5525", "010-9424-5525", "010-9424-5525", "010-9424-5525", "010-9424-5525", "010-9424-5525"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_list_main);

        //변수 초기화
        adapter = new Member_ListAdapter();
        listview = (ListView) findViewById(R.id.member_list_view);

        //어뎁터 할당
        listview.setAdapter(adapter);

        //adapter를 통한 값 전달
        for (int i = 0; i < staff.length; i++) {
            adapter.addVO(staff[i], name[i], major[i], phoneNM[i]);
        }
    }
}