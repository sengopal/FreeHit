package com.debut.ellipsis.freehit.More.Series;

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

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class SeriesPerformance extends Fragment {
    public ViewPager viewPager;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String id = getActivity().getIntent().getStringExtra("id");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        View viewMatchesViewPager = rootView.findViewById(R.id.matches_viewpagegr);

        viewPager = viewMatchesViewPager.findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        View viewMatchCardTabs = rootView.findViewById(R.id.match_card_tabs);
        TabLayout tabLayout = viewMatchCardTabs.findViewById(R.id.tabs);

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

    private void setupViewPager(ViewPager viewPager) {
        SeriesPerformance.ViewPagerAdapter adapter = new SeriesPerformance.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SeriesBattingBowlingPerformance(), "BATTING");
        adapter.addFrag(new SeriesBattingBowlingPerformance(), "BOWLING");
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
            if(mFragmentTitleList.get(0).equals("BATTING"))
            {
                Bundle bundle=new Bundle();
                bundle.putString("sub_fragment_name", "BATTING");
                fragment.setArguments(bundle);
            }
            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("BOWLING")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("sub_fragment_name", "BOWLING");
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