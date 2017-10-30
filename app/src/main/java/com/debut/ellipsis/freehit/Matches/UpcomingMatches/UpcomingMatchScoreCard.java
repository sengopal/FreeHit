package com.debut.ellipsis.freehit.Matches.UpcomingMatches;


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

import com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.CountDownAndFormFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.HeadToHead.HeadToHeadFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.InfoFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.SquadsFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMatchScoreCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matches_match_scorecard);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        String match_name = getIntent().getStringExtra("match_name");

        setTitle(match_name);


        View viewToolbarTabs = findViewById(R.id.toolbar_tabs_matches_scorecard);

        Toolbar toolbar = (Toolbar) viewToolbarTabs.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View viewUpcomingScorecardPager = (View) findViewById(R.id.scorecard_viewpager);

        ViewPager viewPager = (ViewPager) viewUpcomingScorecardPager.findViewById(R.id.viewpager);
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
        UpcomingMatchScoreCard.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        UpcomingMatchScoreCard.ViewPagerAdapter adapter = new UpcomingMatchScoreCard.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InfoFragment(), "INFO");
        adapter.addFrag(new CountDownAndFormFragment(), "SUMMARY");
        adapter.addFrag(new SquadsFragment(), "SQUADS");
        adapter.addFrag(new CommentaryFragment(), "COMMENTARY");
        adapter.addFrag(new HeadToHeadFragment(), "HEAD-TO-HEAD");

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
