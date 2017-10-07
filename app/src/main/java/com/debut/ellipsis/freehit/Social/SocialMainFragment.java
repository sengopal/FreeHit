package com.debut.ellipsis.freehit.Social;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.Matches.MatchesFragment;
import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Social.Polls.SocialPolls;
import com.debut.ellipsis.freehit.Social.Tweets.SocialTweets;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialMainFragment extends Fragment {

    public static Fragment myFragment;

    public ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.poll,
            R.drawable.twitter
    };

    public static final String LOG_TAG = MatchesFragment.class.getSimpleName();

    public SocialMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragment = this;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_social_main, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) rootView.findViewById(R.id.social_tabs);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);

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

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
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
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
