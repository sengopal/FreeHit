package com.debut.ellipsis.freehit.More.Series;

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
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;


public class SeriesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_more_series_activity);

        Intent i = getIntent();
        String Series = i.getStringExtra("CountryName");
        String date=i.getStringExtra("date");
        String teams[]=Series.split(" vs" );
        String team1=teams[0];
        String team2=teams[1];
        Toast.makeText(this, team1, Toast.LENGTH_SHORT).show();

        View viewToolbar = (View) findViewById(R.id.toolbar_tabs_series);
        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(Series.toUpperCase());

        View viewSeriesPager = (View) findViewById(R.id.series_viewpager);

        viewPager = (ViewPager) viewSeriesPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) viewToolbar.findViewById(R.id.tabs);
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
        SeriesActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        SeriesActivity.ViewPagerAdapter adapter = new SeriesActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SeriesPerfo(), "TOP PERFORMANCE");
        adapter.addFrag(new SeriesMatchesFragment(), "SCHEDULE");
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
