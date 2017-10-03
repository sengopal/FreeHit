package com.debut.ellipsis.freehit.Stats.Team;

import android.app.Activity;
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

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
        setContentView(R.layout.stats_team);
        Intent i=getIntent();
        int pos= i.getIntExtra("statname",0);
        toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = new Bundle();
        bundle.putString("p1", String.valueOf(pos));
        // set Fragmentclass Arguments
        Home fragobj = new Home();
        fragobj.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle.putString("p2", String.valueOf(pos));
        // set Fragmentclass Arguments
        Schedule fragobj2 = new Schedule();
        fragobj2.setArguments(bundle2);



        viewPager = (ViewPager) findViewById(R.id.viewpager_teams);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_teams);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0,R.anim.exit_to_right);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        TeamActivity.super.onBackPressed();
        overridePendingTransition(0,R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        TeamActivity.ViewPagerAdapter adapter = new TeamActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Home(), "Home");
        adapter.addFrag(new Schedule(), "BATTING");
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
