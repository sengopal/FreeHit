package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements.Team1ScoreCardFragment;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements.Team2ScoreCardFragment;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.countryHash;

public class ScoreCardFragment extends Fragment {

    public ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView TeamName;
    private ImageView TeamLogo;

    public ScoreCardFragment() {
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
                /*tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
               /* tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);*/

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupTabIcons();

        return rootView;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab);
        tabLayout.getTabAt(1).setCustomView(R.layout.fragment_matchescorecard_scorecard_custom_tab);

        TeamName = (TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.team_sn);
        TeamName.setText("WI");
        TeamName = (TextView) tabLayout.getTabAt(1).getCustomView().findViewById(R.id.team_sn);
        TeamName.setText("ENG");

        TeamLogo = (ImageView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.team_logo);
        String teamLogo = countryHash.getCountryFlag("WEST INDIES");

        MainActivity.requestBuilder = GlideApp.with(getContext()).load(teamLogo).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);
        MainActivity.requestBuilder.into(TeamLogo);

        TeamLogo = (ImageView) tabLayout.getTabAt(1).getCustomView().findViewById(R.id.team_logo);
        teamLogo = countryHash.getCountryFlag("ENGLAND");

        MainActivity.requestBuilder = GlideApp.with(getContext()).load(teamLogo).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);
        MainActivity.requestBuilder.into(TeamLogo);

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
