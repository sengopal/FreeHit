package com.debut.ellipsis.freehit.More.Player;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerActivity extends AppCompatActivity {

    public PlayerItem playerItem;
    private ViewPager viewPager;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        setContentView(R.layout.fragment_more_player_activity);

        Intent i = getIntent();
        String player_name = i.getStringExtra("player_name");
        String player_url = i.getStringExtra("player_url");

        View viewToolbarTabs = findViewById(R.id.toolbar_player);

        Toolbar toolbar = viewToolbarTabs.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(player_name);

        View viewPlayerViewPager = findViewById(R.id.player_viewpager);

        View viewProgress = findViewById(R.id.progress);

        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        viewPager = viewPlayerViewPager.findViewById(R.id.viewpager);
        Call<PlayerItem> call = MainActivity.apiInterface.doGetPlayerInfo(player_url);
        call.enqueue(new Callback<PlayerItem>() {
            @Override
            public void onResponse(Call<PlayerItem> call, Response<PlayerItem> response) {
                mProgressBar.setVisibility(View.INVISIBLE);
                playerItem = response.body();
                setupViewPager(viewPager);
                viewPager.setOffscreenPageLimit(3);

            }

            @Override
            public void onFailure(Call<PlayerItem> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();

            }
        });


        TabLayout tabLayout = viewToolbarTabs.findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PlayerActivity.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }

    private void setupViewPager(ViewPager viewPager) {
        PlayerActivity.ViewPagerAdapter adapter = new PlayerActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new PlayerFragment(), "PLAYER INFO");
        adapter.addFrag(new PlayerFragment(), "BATTING");
        adapter.addFrag(new PlayerFragment(), "BOWLING");
        viewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
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
            if(mFragmentTitleList.get(0).equals("PLAYER INFO"))
            {
                Bundle bundle=new Bundle();
                bundle.putString("fragment_name", "PLAYER INFO");
                fragment.setArguments(bundle);
            }
            if(mFragmentTitleList.size()==2) {
                if (mFragmentTitleList.get(1).equals("BATTING")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "BATTING");
                    fragment.setArguments(bundle);
                }
            }
            if(mFragmentTitleList.size()==3) {
                if (mFragmentTitleList.get(2).equals("BOWLING")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("fragment_name", "BOWLING");
                    fragment.setArguments(bundle);
                }
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public PlayerItem getQuery(){
        return playerItem;
    }
}

