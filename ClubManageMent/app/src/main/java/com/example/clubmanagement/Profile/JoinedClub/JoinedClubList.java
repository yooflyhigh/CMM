package com.example.clubmanagement.Profile.JoinedClub;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clubmanagement.ClubPage.Home.Main_HomePage;
import com.example.clubmanagement.Join.JoinPopUp;
import com.example.clubmanagement.System.Adapter.ListViewAdapter.Fragment_List_Adapter;
import com.example.clubmanagement.ClubPage.Home.ClubPoster;
import com.example.clubmanagement.System.Fragment.Fragment_Start;
import com.example.clubmanagement.System.ListVO.ListVO_Frg;
import com.example.clubmanagement.Profile.UserID;
import com.example.clubmanagement.DataBase.DBConnect.ImageURL.CNT_Image_File;
import com.example.clubmanagement.R;

import java.util.HashMap;

import static com.example.clubmanagement.DataBase.DataPool.Club.Club_Item_list;
import static com.example.clubmanagement.DataBase.DataPool.Member.Club_Member_Item_list;
import static java.lang.Thread.sleep;

public class JoinedClubList extends Fragment implements Fragment_Start.OnBackPressedListener {

    private ListView listview;
    private Fragment_List_Adapter adapter;
    CNT_Image_File ImageDown;

    public static JoinedClubList newInstance() {
        Bundle args = new Bundle();
        JoinedClubList fragment = new JoinedClubList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 메인 메소드
        View v = inflater.inflate(R.layout.frg_joined_club_list, container, false);
        adapter = new Fragment_List_Adapter();
        listview = (ListView) v.findViewById(R.id.List_view);
        listview.setAdapter(adapter);
        ShowList();

        return v;
    }

    private void ShowList() {
        HashMap<String, String> Member;
        HashMap<String, String> Club;
        for (int i = 0; i < Club_Member_Item_list.size(); i++) {
            Member = Club_Member_Item_list.get(i);
            if (Member.get("STUDENT_ID").equals(UserID.UserID)) {
                for(int j = 0; j < Club_Item_list.size();j++) {
                    Club = Club_Item_list.get(j);
                    if (Member.get("CLUB_ID").equals(Club.get("CLUB_ID")) && Member.get("JOIN_CD").equals("1")) {
                        String url = Club.get("INTRO_FILE_NM");
                        ImageDown = new CNT_Image_File(url);
                        ImageDown.run();
                        try {
                            sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        adapter.addVO(new BitmapDrawable(getResources(), ImageDown.bitmap), Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                        break;
                    }
                }
            }
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListVO_Frg SelectedClub = (ListVO_Frg) (listview.getAdapter().getItem(position));
                ClubPoster.image = (BitmapDrawable) SelectedClub.getImg();
                Intent intent = new Intent(getActivity(), Main_HomePage.class);
                intent.putExtra("CLUB_ID",SelectedClub.getCLUB_ID());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}