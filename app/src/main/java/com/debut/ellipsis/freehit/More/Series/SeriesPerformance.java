package com.debut.ellipsis.freehit.More.Series;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class SeriesPerformance extends Fragment {
    public ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String fragment_name = getArguments().getString("fragment_name");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        View viewMatchesViewPager = rootView.findViewById(R.id.matches_viewpagegr);

        viewPager = viewMatchesViewPager.findViewById(R.id.viewpager);


        setupViewPager(viewPager);

        View viewMatchCardTabs = rootView.findViewById(R.id.match_card_tabs);
        TabLayout tabLayout = viewMatchCardTabs.findViewById(R.id.tabs);

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

    private void setupViewPager(ViewPager viewPager) {
        SeriesPerformance.ViewPagerAdapter adapter = new SeriesPerformance.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SeriesBattingPerformance(), "Batting");
        adapter.addFrag(new SeriesBowlingPerformance(), "Bowling");
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