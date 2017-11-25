package com.debut.ellipsis.freehit.Matches.LiveMatches;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryElements.CommentaryFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.HeadToHeadFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.InfoFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardItem;
import com.debut.ellipsis.freehit.Matches.ScoreCard.SummaryFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveMatchScoreCard extends AppCompatActivity {

    private ViewPager viewPager;
    private ProgressBar mProgressBar;
    public static List<ScoreCardItem> LivescoreCardItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_matches_match_scorecard);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

        String match_name = getIntent().getStringExtra("match_name");
        String match_id = getIntent().getStringExtra("match_id");

        setTitle(match_name);

        View viewToolbarTabs = findViewById(R.id.toolbar_tabs_matches_scorecard);

        Toolbar toolbar = viewToolbarTabs.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View viewViewPager = findViewById(R.id.scorecard_viewpager);

        View viewProgress = findViewById(R.id.progress);

        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        viewPager = viewViewPager.findViewById(R.id.viewpager);
        Call<ScoreCardItem> call = MainActivity.apiInterface.doGetLiveMatchScoreCard(match_id);
        call.enqueue(new Callback<ScoreCardItem>() {
            @Override
            public void onResponse(Call<ScoreCardItem> call, Response<ScoreCardItem> response) {
                LivescoreCardItems = response.body().getResults();
                mProgressBar.setVisibility(View.INVISIBLE);
                setupViewPager(viewPager);
                viewPager.setOffscreenPageLimit(5);

            }

            @Override
            public void onFailure(Call<ScoreCardItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), R.string.no_internet_connection , Toast.LENGTH_SHORT).show();
                System.out.println(t.getLocalizedMessage());

            }
        });


        TabLayout tabLayout = viewToolbarTabs.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
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
        LiveMatchScoreCard.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);
    }

    private void setupViewPager(ViewPager viewPager) {
        LiveMatchScoreCard.ViewPagerAdapter adapter = new LiveMatchScoreCard.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InfoFragment(), "INFO");
        adapter.addFrag(new SummaryFragment(), "SUMMARY");
        adapter.addFrag(new ScoreCardFragment(), "SCORE CARD");
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
            if (mFragmentTitleList.get(0).equals("INFO")) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment_name", "LIVE");
                fragment.setArguments(bundle);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static List<ScoreCardItem> getQList() {
        return LivescoreCardItems;
    }

}
