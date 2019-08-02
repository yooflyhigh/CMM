package com.example.clubmanagement.Join;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clubmanagement.System.Adapter.ListViewAdapter.Fragment_List_Adapter;
import com.example.clubmanagement.ClubPage.Home.ClubPoster;
import com.example.clubmanagement.DataBase.DBConnect.ImageURL.CNT_Image_File;
import com.example.clubmanagement.System.Fragment.Fragment_Start;
import com.example.clubmanagement.System.ListVO.ListVO_Frg;
import com.example.clubmanagement.R;

import java.util.HashMap;

import static java.lang.Thread.sleep;
import static com.example.clubmanagement.DataBase.DataPool.Club.Club_Item_list;

public class Center extends Fragment implements Fragment_Start.OnBackPressedListener{
    private ListView listview;
    private Fragment_List_Adapter adapter;

    Button applyUp;
    CNT_Image_File ImageDown;
    boolean flag = false;
    int Pos = Integer.MAX_VALUE ;

    public static Center newInstance() {
        Bundle args = new Bundle();
        Center fragment = new Center();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_center_list, container, false);

        Spinner checkSpinner = (Spinner) v.findViewById(R.id.spinner_Check);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.major, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        checkSpinner.setAdapter(Adapter);

        checkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int SpinnerNumber, long id) {
                adapter = new Fragment_List_Adapter();
                ShowList(SpinnerNumber);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listview = (ListView) v.findViewById(R.id.List_view);
        ApplyStart(v);

        return v;
    }
    private void ShowList(int SpinnerNumber) {
        listview.setAdapter(adapter);
        HashMap<String, String> Club;
        for (int i = 0; i < Club_Item_list.size(); i++) {
            Club = Club_Item_list.get(i);
            String url = Club.get("INTRO_FILE_NM");
            ImageDown = new CNT_Image_File(url);
            ImageDown.run();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ClubPoster.image = new BitmapDrawable(getResources(),ImageDown.bitmap);
            if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 0) {
                adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 1) {
                if (Club.get("CLUB_AT_CD").equals("2001")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 2) {
                if (Club.get("CLUB_AT_CD").equals("2002")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 3) {
                if (Club.get("CLUB_AT_CD").equals("2003")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 4) {
                if (Club.get("CLUB_AT_CD").equals("2004")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 5) {
                if (Club.get("CLUB_AT_CD").equals("2005")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && SpinnerNumber == 6) {
                if (Club.get("CLUB_AT_CD").equals("2006")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_ID"), Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            }
        }
    }

    private void ApplyStart(final View v) {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pos = position;
            }
        });

        applyUp = (Button) v.findViewById(R.id.button);
        applyUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Pos < listview.getCount()){
                    ListVO_Frg SelectedClub = (ListVO_Frg)(listview.getAdapter().getItem(Pos));
                    ClubPoster.image = (BitmapDrawable) SelectedClub.getImg();
                    Intent intent = new Intent(getActivity(), JoinPopUp.class);
                    intent.putExtra("CLUB_ID",SelectedClub.getCLUB_ID());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(),"동아리를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        //fragment_start로 가서 이벤트 뒤로가기 이벤트 처리
    }
}