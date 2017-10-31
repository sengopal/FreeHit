package com.debut.ellipsis.freehit.More.Team;

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


public class TeamMatchesFragment extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;

    public TeamMatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches, container, false);


        View viewMatchesViewPager = rootView.findViewById(R.id.matches_viewpagegr);

        viewPager = (ViewPager) viewMatchesViewPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        View viewMatchCardTabs = rootView.findViewById(R.id.match_card_tabs);
        tabLayout = (TabLayout) viewMatchCardTabs.findViewById(R.id.tabs);

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
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
            if(mFragmentTitleList.get(0).equals("UPCOMING"))
            {
                Bundle bundle=new Bundle();
                bundle.putString("fragment_name", "UPCOMING");
                fragment.setArguments(bundle);
            }
            if(mFragmentTitleList.size()==2) {
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
