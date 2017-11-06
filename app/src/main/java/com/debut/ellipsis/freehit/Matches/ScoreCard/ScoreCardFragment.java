package com.debut.ellipsis.freehit.Matches.ScoreCard;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElementsTemp.Team1ScoreCardFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElementsTemp.Team2ScoreCardFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class ScoreCardFragment extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;
    private String team_1;
    private String team_2;
    private List<ScoreCardItem> teamList = null;

    public ScoreCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String match_type = getActivity().getIntent().getStringExtra("match_type");
        String team_1_intent = getActivity().getIntent().getStringExtra("team1");
        String team_2_intent = getActivity().getIntent().getStringExtra("team2");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_social_main, container, false);

        View viewSocialPager = rootView.findViewById(R.id.social_viewpager);

        viewPager = viewSocialPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        View viewTabSocial = rootView.findViewById(R.id.social_tabs);
        tabLayout = viewTabSocial.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            tabLayout.setBackgroundColor(getResources().getColor(R.color.night_background));
            viewPager.setBackgroundColor(getResources().getColor(R.color.night_background));
        }

        if (match_type.equals("PAST")) {
            teamList = PastMatchScoreCard.getQList();
        }


        if(teamList.get(0).getScorecard().getTeam1().getInncount()!=0)
        {
            if(teamList.get(0).getScorecard().getTeam1().getTeamname().equals(team_1_intent)) {
                team_1 = team_1_intent;
                team_2 = team_2_intent;
            }
            else
            {
                team_1 = team_2_intent;
                team_2 = team_1_intent;
            }
        }

        else if(teamList.get(0).getScorecard().getTeam2().getInncount()!=0)
        {
            if(teamList.get(0).getScorecard().getTeam2().getTeamname().equals(team_2_intent)) {
                team_2 = team_2_intent;
                team_1 = team_1_intent;
            }
            else
            {
                team_2 = team_1_intent;
                team_1 =team_2_intent;
            }
        }


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
        setupTabIcons();


        return rootView;
    }

    private void setupTabIcons() {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                tabLayout.getTabAt(0).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab_dark);
                tabLayout.getTabAt(1).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab_dark);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                tabLayout.getTabAt(0).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab);
                tabLayout.getTabAt(1).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab);
                break;
        }


        TextView teamName = tabLayout.getTabAt(0).getCustomView().findViewById(R.id.team_sn);
        teamName.setText(team_1);
        teamName = tabLayout.getTabAt(1).getCustomView().findViewById(R.id.team_sn);
        teamName.setText(team_2);

        ImageView teamLogo1 = tabLayout.getTabAt(0).getCustomView().findViewById(R.id.team_logo);

        String teamLogo = WelcomeActivity.countryHash.getCountryFlag(team_1.toUpperCase());

        MainActivity.requestBuilder = GlideApp.with(getContext()).load(teamLogo).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);
        MainActivity.requestBuilder.into(teamLogo1);

        teamLogo1 = tabLayout.getTabAt(1).getCustomView().findViewById(R.id.team_logo);

        teamLogo = WelcomeActivity.countryHash.getCountryFlag(team_2.toUpperCase());

        MainActivity.requestBuilder = GlideApp.with(getContext()).load(teamLogo).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);
        MainActivity.requestBuilder.into(teamLogo1);

    }


    private void setupViewPager(ViewPager viewPager) {
        ScoreCardFragment.ViewPagerAdapter adapter = new ScoreCardFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new Team1ScoreCardFragment());
        adapter.addFrag(new Team2ScoreCardFragment());
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

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

        public void addFrag(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }
}
