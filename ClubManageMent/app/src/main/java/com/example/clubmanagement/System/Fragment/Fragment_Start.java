package com.example.clubmanagement.System.Fragment;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.clubmanagement.System.Adapter.FragmentAdapter.Fragment_Page_Adapter;
import com.example.clubmanagement.R;
import com.example.clubmanagement.System.Listener.BackPressCloseHandler;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public class Fragment_Start extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BackPressCloseHandler BackPressCloseHandler = new BackPressCloseHandler(this);
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frg_frgment_start);

        setToolbar();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment_Page_Adapter mFrgmntPageAdapter = new Fragment_Page_Adapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mFrgmntPageAdapter);
        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi_main,menu);
        return true;
    }
    //TODO : 계정화면 정상화 시키고 로그아웃 구현하기
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case -1000132:
                Snackbar.make(toolbar, (CharSequence)"Logout menu pressed", -1).show();
                break;
            case -1000091:
                Snackbar.make(toolbar, (CharSequence)"Search menu pressed", -1).show();
                break;
            case -1000055:
                Snackbar.make(toolbar, (CharSequence)"Account menu pressed", -1).show();
                break;
            case android.R.id.home:
                ((DrawerLayout)this.findViewById(R.id.drawerLayout)).openDrawer(Gravity.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.account:
                Snackbar.make(findViewById(R.id.account),"Navigation Account pressed",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Snackbar.make(findViewById(R.id.setting),"Navigation Setting pressed",Snackbar.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.closeDrawers();
        return false;
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
                }
            }
        }
    }
}