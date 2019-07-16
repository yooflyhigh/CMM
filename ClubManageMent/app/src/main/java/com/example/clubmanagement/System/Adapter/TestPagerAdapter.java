package com.example.clubmanagement.System.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.clubmanagement.Profile.JoinedClub.JoinedClubList;
import com.example.clubmanagement.Join.Center;
import com.example.clubmanagement.Join.Major;

public class TestPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_NUMBER = 3;
    public TestPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return JoinedClubList.newInstance();
            case 1:
                return Major.newInstance();
            case 2:
                return Center.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "가입된 동아리";
            case 1:
                return "전공 동아리";
            case 2:
                return "중앙 동아리";
            default:
                return null;
        }
    }

}