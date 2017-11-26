package com.debut.ellipsis.freehit.More.Series;

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

import com.debut.ellipsis.freehit.More.TeamSeriesMatchesFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;


public class SeriesActivity extends AppCompatActivity {

    public static String Series_Name ;
    public static String date;
    public static String Teams ;
    public static String id;

    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_more_series_activity);

        Series_Name = getIntent().getStringExtra("Series_Name");

        View viewToolbar = findViewById(R.id.toolbar_tabs_series);

        Toolbar toolbar = viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(Series_Name);

        View viewSeriesPager = findViewById(R.id.series_viewpager);

        ViewPager viewPager = viewSeriesPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = viewToolbar.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(2);



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
        adapter.addFrag(new TeamSeriesMatchesFragment(), "SCHEDULE");
        adapter.addFrag(new SeriesPerformance(), "TOP PERFORMANCE");
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
            if(mFragmentTitleList.get(0).equals("SCHEDULE"))
            {
                Bundle bundle=new Bundle();
                bundle.putString("fragment_name", "SERIES SCHEDULE");
                fragment.setArguments(bundle);
            }
            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("TOP PERFORMANCE")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "SERIES SCHEDULE");
                    fragment.setArguments(bundle);
                }
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
