package com.example.clubmanagement.Fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clubmanagement.Adapter.ListViewAdapter;
import com.example.clubmanagement.ClubPage.Club_page;
import com.example.clubmanagement.Profile.UserID;
import com.example.clubmanagement.DataBase.DBConnect.ImageURL.CNT_Image_File;
import com.example.clubmanagement.R;

import java.util.HashMap;

import static com.example.clubmanagement.DataBase.DataPool.Club.Club_Item_list;
import static com.example.clubmanagement.DataBase.DataPool.Member.Club_Member_Item_list;
import static java.lang.Thread.sleep;

public class PageOneFragment extends Fragment {
    private ListView listview;
    private ListViewAdapter adapter;
    HashMap<String, String> Club;
    HashMap<String, String> Member;
    String[] NameArr;
    int count;
    CNT_Image_File Club_Poster;

    public static PageOneFragment newInstance() {
        Bundle args = new Bundle();
        PageOneFragment fragment = new PageOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 메인 메소드
        View v = inflater.inflate(R.layout.fragment_page_one, container, false);
        adapter = new ListViewAdapter();
        listview = (ListView) v.findViewById(R.id.List_view);
        listview.setAdapter(adapter);
        ShowList();

        return v;
    }

    private void ShowList() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listview.setAdapter(adapter);

        NameArr = new String[Club_Item_list.size()];
        count = 0;

        for (int i = 0; i < Club_Member_Item_list.size(); i++) {
            Member = Club_Member_Item_list.get(i);
            if (Member.get("STUDENT_ID").equals(UserID.UserID)) {
                Club = Club_Item_list.get(i);
                if (Member.get("CLUB_ID").equals(Club.get("CLUB_ID"))) {
                    NameArr[count++] = Club.get("CLUB_ID");
                    String url = Club.get("INTRO_FILE_NM");
                    Club_Poster = new CNT_Image_File(url);
                    Club_Poster.run();
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    adapter.addVO(new BitmapDrawable(getResources(), Club_Poster.bitmap), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), (position + 1) + "번째 리스트가 클릭되었습니다.", Toast.LENGTH_SHORT).show();
                //ClubPositon.position = NameArr[position];
                startActivity(new Intent(getActivity(), Club_page.class));
            }
        });

    }
}