package com.debut.ellipsis.freehit.More;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.More.Series.SeriesMatches;
import com.debut.ellipsis.freehit.More.Team.TeamMatches;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class TeamSeriesMatchesFragment extends Fragment {
    public ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String fragment_name = getArguments().getString("fragment_name");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        View viewMatchesViewPager = rootView.findViewById(R.id.matches_viewpagegr);

        viewPager = (ViewPager) viewMatchesViewPager.findViewById(R.id.viewpager);

        if(fragment_name.equals("SERIES SCHEDULE")) {
            setupViewPagerSeries(viewPager);
        }
        if(fragment_name.equals("TEAM SCHEDULE"))
        {
            setupViewPagerTean(viewPager);
        }

        View viewMatchCardTabs = rootView.findViewById(R.id.match_card_tabs);
        TabLayout tabLayout = (TabLayout) viewMatchCardTabs.findViewById(R.id.tabs);


        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            tabLayout.setBackgroundColor(Color.parseColor("#2c2c35"));
        }

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    private void setupViewPagerSeries(ViewPager viewPager) {
        TeamSeriesMatchesFragment.ViewPagerAdapter adapter = new TeamSeriesMatchesFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SeriesMatches(), "UPCOMING");
        adapter.addFrag(new SeriesMatches(), "PAST");
        viewPager.setAdapter(adapter);

    }

    private void setupViewPagerTean(ViewPager viewPager) {
        TeamSeriesMatchesFragment.ViewPagerAdapter adapter = new TeamSeriesMatchesFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new TeamMatches(), "UPCOMING");
        adapter.addFrag(new TeamMatches(), "PAST");
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
            if (mFragmentTitleList.get(0).equals("UPCOMING")) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment_name", "UPCOMING");
                fragment.setArguments(bundle);
            }
            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("PAST")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "PAST");
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
