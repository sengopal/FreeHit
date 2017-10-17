package com.debut.ellipsis.freehit.IntoSlider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;


public class WelcomeActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;
    private boolean clicked = false;
    CountryHash countryHash = new CountryHash();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);


        View viewViewPager = (View) findViewById(R.id.welcome_viewpager);

        viewPager = (ViewPager) viewViewPager.findViewById(R.id.viewpager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4,
                R.layout.welcome_slide5_country_picker};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    if (clicked) {
                        launchHomeScreen();
                    } else {

                        LayoutInflater inflater = getLayoutInflater();
                        View layouttoast = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toastcustom));
                        ((TextView) layouttoast.findViewById(R.id.texttoast)).setText(R.string.select_team_alert);

                        Toast mytoast = new Toast(getBaseContext());
                        mytoast.setView(layouttoast);
                        mytoast.setDuration(Toast.LENGTH_SHORT);
                        mytoast.show();

                    }

                    //then on another method or where you want

                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new ImageView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        int width = getResources().getDimensionPixelSize(R.dimen._15sdp);
        ;
        int height = getResources().getDimensionPixelSize(R.dimen._15sdp);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            dots[i].setLayoutParams(params);
            params.setMargins(15, 15, 0, 0);
            //parms.setMargins(left, top, right, bottom);
            dots[i].setImageResource(R.drawable.circle);
            dots[i].setColorFilter(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            dots[currentPage].setLayoutParams(params);
            params.setMargins(15, 15, 0, 0);

            dots[currentPage].setImageResource(R.drawable.ball);
            dots[currentPage].setColorFilter(colorsActive[currentPage]);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);

            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            if (position == 4) {
                ImageView country_flag = (ImageView) findViewById(R.id.country_flag);

                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String name = prefs.getString("country_name", "SELECT COUNTRY");


                TextView country_name = (TextView) findViewById(R.id.country_name);
                country_name.setText(name);

                String flagID = prefs.getString("country_flag", "");
                Log.e("test", flagID);

                String TeamLogo = countryHash.getCountryFlag(name.toUpperCase());

                /*CustomImageSizeModel Flag = new CustomImageSizeModelFutureStudio(TeamLogo);*/

                RequestBuilder requestBuilder = GlideApp.with(getBaseContext()).load(TeamLogo).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

                requestBuilder.into(country_flag);

                /*Glide.with(getBaseContext()).load(Flag).apply(new RequestOptions().placeholder(R.drawable.matches)).into(country_flag);*/

            }
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    public void select_country(View view) {

        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String flagURLID) {
                // Implement your code here
                TextView country_name = (TextView) findViewById(R.id.country_name);
                country_name.setText(name);

                ImageView before = (ImageView) findViewById(R.id.country_flag);

                /*CustomImageSizeModel Flag = new CustomImageSizeModelFutureStudio(flagURLID);*/

                /*Glide.with(getApplicationContext()).load(flagURLID).apply(new RequestOptions().placeholder(R.drawable.matches)).into(before);*/

                RequestBuilder requestBuilder = GlideApp.with(getBaseContext()).load(flagURLID).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

                requestBuilder.into(before);

                TextView description = (TextView) findViewById(R.id.slide5description);
                description.setVisibility(View.GONE);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("country_name", name);
                editor.apply();

                clicked = true;
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }

}
