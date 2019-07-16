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

import com.example.clubmanagement.System.Adapter.ListViewAdapter;
import com.example.clubmanagement.ClubPage.Home.ClubPoster;
import com.example.clubmanagement.DataBase.DBConnect.ImageURL.CNT_Image_File;
import com.example.clubmanagement.System.ListVO.ListVO;
import com.example.clubmanagement.R;

import java.util.HashMap;

import static java.lang.Thread.sleep;
import static com.example.clubmanagement.DataBase.DataPool.Club.Club_Item_list;

public class Center extends Fragment {
    private ListView listview;
    private ListViewAdapter adapter;
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
        View v = inflater.inflate(R.layout.fragment_page_three, container, false);

        Spinner checkSpinner = (Spinner) v.findViewById(R.id.spinner_Check);
        ArrayAdapter Adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.major, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        checkSpinner.setAdapter(Adapter);

        checkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter = new ListViewAdapter();
                DataInput(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listview = (ListView) v.findViewById(R.id.List_view);
        ApplyStart(v);

        return v;
    }
    private void DataInput(int Code) {
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
            if (Club.get("CLUB_GB_CD").equals("1002") && Code == 0) {
                adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 1) {
                if (Club.get("CLUB_AT_CD").equals("2001")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 2) {
                if (Club.get("CLUB_AT_CD").equals("2002")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 3) {
                if (Club.get("CLUB_AT_CD").equals("2003")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 4) {
                if (Club.get("CLUB_AT_CD").equals("2004")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 5) {
                if (Club.get("CLUB_AT_CD").equals("2005")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
                }
            } else if (Club.get("CLUB_GB_CD").equals("1002") && Code == 6) {
                if (Club.get("CLUB_AT_CD").equals("2006")) {
                    adapter.addVO(ClubPoster.image, Club.get("CLUB_NM"), Club.get("INTRO_CONT"));
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
                    ListVO Vo = (ListVO)(listview.getAdapter().getItem(Pos));
                    ClubPoster.image = (BitmapDrawable) Vo.getImg();
                    Intent intent = new Intent(getActivity(), JoinPopUp.class);
                    startActivityForResult(intent, 1);
                }
                else{
                    Toast.makeText(getContext(),"동아리를 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}