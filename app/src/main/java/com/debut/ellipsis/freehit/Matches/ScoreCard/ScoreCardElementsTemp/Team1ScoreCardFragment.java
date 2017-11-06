package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElementsTemp;


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
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardItem;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class Team1ScoreCardFragment extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;
    private List<ScoreCardItem> teamList = null;

    public Team1ScoreCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String match_type = getActivity().getIntent().getStringExtra("match_type");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_social_main, container, false);

        View viewSocialPager = rootView.findViewById(R.id.social_viewpager);

        viewPager = viewSocialPager.findViewById(R.id.viewpager);


        View viewTabSocial = rootView.findViewById(R.id.social_tabs);
        tabLayout = viewTabSocial.findViewById(R.id.tabs);

        if (match_type.equals("PAST")) {
            teamList = ((PastMatchScoreCard) getActivity()).getQList();
        }

        TextView no_match_data = rootView.findViewById(R.id.No_match_data);

        if (teamList.get(0).getScorecard().getTeam1().getInncount() == 1)
            setupViewPagerOneInnings(viewPager);
        else if(teamList.get(0).getScorecard().getTeam1().getInncount() == 2)
            setupViewPagerTwoInnings(viewPager);
        else
        {
            viewPager.setVisibility(View.INVISIBLE);
            no_match_data.setVisibility(View.VISIBLE);
            no_match_data.setText(R.string.no_match_data);
        }


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            tabLayout.setBackgroundColor(Color.parseColor("#36393f"));
            viewPager.setBackgroundColor(getResources().getColor(R.color.night_background));
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


    private void setupViewPagerTwoInnings(ViewPager viewPager) {
        Team1ScoreCardFragment.ViewPagerAdapter adapter = new Team1ScoreCardFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new Innings1_Innings2(), "Innings 1");
        adapter.addFrag(new Innings1_Innings2(), "Innings 2");
        viewPager.setAdapter(adapter);

    }

    private void setupViewPagerOneInnings(ViewPager viewPager) {
        Team1ScoreCardFragment.ViewPagerAdapter adapter = new Team1ScoreCardFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new Innings1_Innings2(), "Innings 1");
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
            if (mFragmentTitleList.get(0).equals("Innings 1")) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment_name", "Team_1_Innings_1");
                fragment.setArguments(bundle);
            }

            if (mFragmentTitleList.size() == 2) {
                if (mFragmentTitleList.get(1).equals("Innings 2")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "Team_1_Innings_2");
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
