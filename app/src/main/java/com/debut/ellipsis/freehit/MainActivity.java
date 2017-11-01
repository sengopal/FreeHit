package com.debut.ellipsis.freehit;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.debut.ellipsis.freehit.Matches.MatchesFragment;
import com.debut.ellipsis.freehit.More.MoreMain.MoreFragment;
import com.debut.ellipsis.freehit.News.NewsFragment;
import com.debut.ellipsis.freehit.Settings.CustomSettings;
import com.debut.ellipsis.freehit.Social.SocialMainFragment;
import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.matches,
            R.drawable.news,
            R.drawable.social,
            R.drawable.more,
    };

    public static RequestBuilder requestBuilder;
    public static APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //  If we have to define custom configuration, uncomment next line and don't forget to add consumer Key and Secret Key.
        //TwitterConfig config = new TwitterConfig.Builder(this).logger(new DefaultLogger(Log.DEBUG)).twitterAuthConfig(new TwitterAuthConfig("FREEHIT_CONSUMER_KEY","FREEHIT_CONSUMER_SECRET")).debug(true).build();

        // Initializing Twitter Kit
        Twitter.initialize(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView AppTitle = (TextView) findViewById(R.id.title) ;
        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "strasua.ttf");

        AppTitle.setTypeface(tfTitle);


        View viewMainPager = findViewById(R.id.main_viewpager);

        viewPager = (ViewPager) viewMainPager.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(4);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(Color.parseColor("#f5f5f5"), PorterDuff.Mode.SRC_IN);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupTabIcons();
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#f5f5f5"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MatchesFragment(), "MATCHES");
        adapter.addFrag(new NewsFragment(), "NEWS");
        adapter.addFrag(new SocialMainFragment(), "SOCIAL");
        adapter.addFrag(new MoreFragment(), "MORE");
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
            if(mFragmentTitleList.size()==2) {
                if (mFragmentTitleList.get(1).equals("NEWS")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "NEWS");
                    fragment.setArguments(bundle);
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, CustomSettings.class);
            startActivity(settingsIntent);

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);
    }

}
