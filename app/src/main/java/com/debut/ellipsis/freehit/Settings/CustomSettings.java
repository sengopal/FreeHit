package com.debut.ellipsis.freehit.Settings;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModel;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModelFutureStudio;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.CountryPicker;
import com.debut.ellipsis.freehit.IntoSlider.CountryPickerListener;
import com.debut.ellipsis.freehit.R;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

public class CustomSettings extends AppCompatActivity {

    private Toolbar toolbar;
    CountryHash countryHash = new CountryHash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_settings);

        View viewToolbar = (View) findViewById(R.id.custom_settings_toolbar);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = "Settings";

        //To Set The Color Of The Action Bar
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);


        ImageView country_flag = (ImageView) findViewById(R.id.country_flag);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("country_name", "Select Your Favourite Country");

        TextView country_name = (TextView) findViewById(R.id.country_name);
        country_name.setText(name);

        String TeamLogoURL = countryHash.getCountryFlag(name.toUpperCase());

        CustomImageSizeModel TeamLogo = new CustomImageSizeModelFutureStudio(TeamLogoURL);

        RequestBuilder requestBuilder = GlideApp.with(getBaseContext()).load(TeamLogo).placeholder(R.drawable.matches);

        requestBuilder.into(country_flag);

        /*Glide.with(getApplicationContext()).load(TeamLogo).apply(new RequestOptions().placeholder(R.drawable.matches)).into(country_flag);*/

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

                CustomImageSizeModel TeamLogo = new CustomImageSizeModelFutureStudio(flagURLID);

                RequestBuilder requestBuilder = GlideApp.with(getBaseContext()).load(TeamLogo).placeholder(R.drawable.matches);

                requestBuilder.into(before);

                /*GlideApp.with(getBaseContext()).load(TeamLogo).apply(new RequestOptions().placeholder(R.drawable.matches)).into(before);*/

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("country_name", name);
                editor.apply();
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }


}