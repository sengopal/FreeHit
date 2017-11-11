package com.debut.ellipsis.freehit.Social;


import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.debut.ellipsis.freehit.Social.Polls.SocialPolls;
import com.debut.ellipsis.freehit.Social.Tweets.SocialTweets;

import java.util.ArrayList;
import java.util.List;

public class SocialMainFragment extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.poll_vector,
            R.drawable.twitter_vector
    };

    public SocialMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_social_main, container, false);

        View viewSocialPager = rootView.findViewById(R.id.social_viewpager);

        viewPager = (ViewPager) viewSocialPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        View viewTabSocial = rootView.findViewById(R.id.social_tabs);
        tabLayout = (TabLayout) viewTabSocial.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (AppCompatDelegate.getDefaultNightMode()) {
                    case AppCompatDelegate.MODE_NIGHT_YES:
                        tab.getIcon().setColorFilter(Color.parseColor("#f5f5f5"), PorterDuff.Mode.SRC_IN);
                        break;
                    case AppCompatDelegate.MODE_NIGHT_NO:
                        tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (AppCompatDelegate.getDefaultNightMode()) {
                    case AppCompatDelegate.MODE_NIGHT_YES:
                        tab.getIcon().setColorFilter(Color.parseColor("#6c6c6d"), PorterDuff.Mode.SRC_IN);
                        break;
                    case AppCompatDelegate.MODE_NIGHT_NO:
                        tab.getIcon().setColorFilter(Color.parseColor("#c2c2c2"), PorterDuff.Mode.SRC_IN);
                        break;
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupTabIcons();

        return rootView;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);




        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#f5f5f5"), PorterDuff.Mode.SRC_IN);
                tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#6c6c6d"), PorterDuff.Mode.SRC_IN);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                tabLayout.getTabAt(0).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#c2c2c2"), PorterDuff.Mode.SRC_IN);
                break;
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        SocialMainFragment.ViewPagerAdapter adapter = new SocialMainFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new SocialPolls(), "POLLS");
        adapter.addFrag(new SocialTweets(), "TWEETS");
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
            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("TWEETS")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "TWEETS");
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
