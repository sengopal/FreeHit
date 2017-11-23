package com.debut.ellipsis.freehit.More.Rankings;

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
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RankingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar mProgressBar;
    public List<RankingItem> QueryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.fragment_more_rankings_activity);

        View viewToolbar = findViewById(R.id.toolbar_tabs_ranking);
        toolbar = viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.rankings);

        View viewProgress = findViewById(R.id.progress);

        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        View viewRankingPager = findViewById(R.id.ranking_viewpager);

        viewPager = viewRankingPager.findViewById(R.id.viewpager);

        Call<RankingItem> call = MainActivity.apiInterface.doGetRankingResources();
        call.enqueue(new Callback<RankingItem>() {
            @Override
            public void onResponse(Call<RankingItem> call, Response<RankingItem> response) {
                if(response.isSuccessful()) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    QueryList = response.body().getResults();
                    setupViewPager(viewPager);
                    viewPager.setOffscreenPageLimit(3);
                }
                else
                {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), R.string.server_issues, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RankingItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();

            }
        });

        tabLayout = viewToolbar.findViewById(R.id.tabs);
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
        RankingActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);
    }

    private void setupViewPager(ViewPager viewPager) {
        RankingActivity.ViewPagerAdapter adapter = new RankingActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RankingFragment(), "TEAMS");
        adapter.addFrag(new RankingFragment(), "BATSMEN");
        adapter.addFrag(new RankingFragment(), "BOWLER");
        adapter.addFrag(new RankingFragment(), "ALL ROUNDER");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();//j
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
            if (mFragmentTitleList.get(0).equals("TEAMS")) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment_name", "TEAMS");
                fragment.setArguments(bundle);
            }
            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("BATSMEN")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "BATSMEN");
                    fragment.setArguments(bundle);
                }
            }
            if (mFragmentTitleList.size() == 3) {
                if (mFragmentTitleList.get(2).equals("BOWLER")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "BOWLER");
                    fragment.setArguments(bundle);
                }
            }
            if (mFragmentTitleList.size() == 4) {
                if (mFragmentTitleList.get(3).equals("ALL ROUNDER")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "ALL ROUNDER");
                    fragment.setArguments(bundle);
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public List<RankingItem> getQList() {
        return QueryList;
    }

}
