package com.debut.ellipsis.freehit.More.Team;

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

public class TeamActivity extends AppCompatActivity {

    public static int Team = 0;
    public static String favTeam = null;
    public static String tempTeamName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.fragment_more_team_activity);
        Intent i = getIntent();
        Team = i.getIntExtra("CountryName", 0);
        favTeam = i.getStringExtra("fav_country");

        if(Team == 0)
        {
            tempTeamName = favTeam;
        }
        else
        {
            tempTeamName = this.getApplicationContext().getString(Team);
        }

        View viewToolbarTabs = findViewById(R.id.team_toolbar_tabs);

        Toolbar toolbar = (Toolbar) viewToolbarTabs.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(tempTeamName);

        View viewTeamPager = findViewById(R.id.teams_viewpager);

        ViewPager viewPager = (ViewPager) viewTeamPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) viewToolbarTabs.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
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
        TeamActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        TeamActivity.ViewPagerAdapter adapter = new TeamActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TeamPlayers(), "PLAYERS");
        adapter.addFrag(new TeamMatchesFragment(), "SCHEDULE");
        adapter.addFrag(new TeamNews(), "NEWS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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
