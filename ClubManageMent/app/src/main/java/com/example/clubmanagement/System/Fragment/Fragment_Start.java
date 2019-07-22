package com.example.clubmanagement.System.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clubmanagement.System.Adapter.FragmentAdapter.Fragment_Page_Adapter;
import com.example.clubmanagement.R;
import com.example.clubmanagement.System.Listener.BackPressCloseHandler;

import java.util.List;

public class Fragment_Start extends AppCompatActivity {
    private BackPressCloseHandler BackPressCloseHandler = new BackPressCloseHandler(this);

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
                    //브렌치에서 테스트중
                }
            }
        }
    }
}