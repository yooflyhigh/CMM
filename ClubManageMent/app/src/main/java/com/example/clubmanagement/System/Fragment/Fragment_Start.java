package com.example.clubmanagement.System.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clubmanagement.System.Adapter.FragmentAdapter.Fragment_Page_Adapter;
import com.example.clubmanagement.R;

public class Fragment_Start extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_frgment_start);
        Fragment_Page_Adapter mFrgmntPageAdapter = new Fragment_Page_Adapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mFrgmntPageAdapter);
        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
    }
}