package com.debut.ellipsis.freehit.More.Player;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    public static String player_name;
    public static String player_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.fragment_more_player_activity);

        Intent i = getIntent();
        player_name = i.getStringExtra("player_name");
        player_url = i.getStringExtra("player_url");

        View viewToolbarTabs = findViewById(R.id.toolbar_player);

        Toolbar toolbar = (Toolbar) viewToolbarTabs.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(player_name);

        View viewPlayerViewPager = findViewById(R.id.player_viewpager);

        ViewPager viewPager = (ViewPager) viewPlayerViewPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) viewToolbarTabs.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PlayerActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        PlayerActivity.ViewPagerAdapter adapter = new PlayerActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Info_Fragment(), "PLAYER INFO");
        adapter.addFrag(new Batting_Fragment(), "BATTING");
        adapter.addFrag(new Bowling_Fragment(), "BOWLING");
        adapter.addFrag(new CareerFragment(), "CAREER");
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}

