package com.debut.ellipsis.freehit.Matches;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchCard;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCard;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    public ViewPager viewPager;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);

        View viewMatchesViewPager = rootView.findViewById(R.id.matches_viewpagegr);

        viewPager = viewMatchesViewPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        View viewMatchCardTabs = rootView.findViewById(R.id.match_card_tabs);
        TabLayout tabLayout = viewMatchCardTabs.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new LiveMatchCard(), "LIVE");
        adapter.addFrag(new UpcomingMatchCard(), "UPCOMING");
        adapter.addFrag(new PastMatchCard(), "RECENT");
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



