package com.debut.ellipsis.freehit.Settings;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.IntoSlider.CountryPicker;
import com.debut.ellipsis.freehit.IntoSlider.CountryPickerListener;
import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

public class CustomSettings extends AppCompatActivity {

    public Button NoConnectionButton;
    public TextView NoConnection;
    public ImageView NoConnectionImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_settings);

        View viewToolbar = findViewById(R.id.custom_settings_toolbar);

        Toolbar toolbar = viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final View no_internet_connection = findViewById(R.id.Unavailable_connection);

        Switch auto_refresh = findViewById(R.id.switch_auto_refresh_mode);

        Switch NightMode = findViewById(R.id.switch_night_mode);

        Button country_select_button = findViewById(R.id.country_select);

        final TextView title_country_select = findViewById(R.id.title_country_select);
        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        NoConnection = no_internet_connection.findViewById(R.id.no_internet_connection_text);

        NoConnectionImage = no_internet_connection.findViewById(R.id.no_internet_connection);

        RelativeLayout country_select = findViewById(R.id.country_select_layout);

        ImageView country_flag = findViewById(R.id.country_flag);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String name = prefs.getString("country_name", "Select Your Favourite Country");

        final TextView country_name = findViewById(R.id.country_name);

        final TextView night_mode = findViewById(R.id.night_mode);

        TextView auto_refresh_mode = findViewById(R.id.auto_refresh_mode);

        String TeamLogoURL = WelcomeActivity.countryHash.getCountryFlag(name.toUpperCase());

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                setTheme(R.style.AppThemeDark);
                title_country_select.setTextColor(Color.WHITE);
                country_name.setTextColor(Color.WHITE);
                night_mode.setTextColor(Color.WHITE);
                country_select_button.setBackgroundResource(R.drawable.button_shape_dark);
                country_select_button.setTextColor(Color.BLACK);
                NoConnection.setTextColor(Color.WHITE);
                NoConnectionButton.setTextColor(Color.BLACK);
                NoConnectionImage.setColorFilter(Color.WHITE);
                auto_refresh_mode.setTextColor(Color.WHITE);
                break;
            default:
                setTheme(R.style.AppTheme);
                NoConnection.setTextColor(Color.BLACK);
                break;
        }

        final View.OnClickListener mClickListener;

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);

            }
        };

        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("auto_refresh", true);
        editor.apply();

        NightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("switch_state", true);
                    editor.apply();
                    night_mode.setOnClickListener(mClickListener);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("switch_state", false);
                    editor.apply();
                    night_mode.setOnClickListener(mClickListener);
                }
            }
        });

        auto_refresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                    editor.putBoolean("auto_refresh", true);
                    editor.apply();
                } else {
                    editor.putBoolean("auto_refresh", false);
                    editor.apply();
                }
            }
        });

        Boolean AutoRereshState = prefs.getBoolean("auto_refresh", false);
        auto_refresh.setChecked(AutoRereshState);

        NightMode.setOnClickListener(mClickListener);

        Boolean NightModeState = prefs.getBoolean("switch_state", false);
        NightMode.setChecked(NightModeState);


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            country_name.setText(name);

            switch (AppCompatDelegate.getDefaultNightMode()) {
                case AppCompatDelegate.MODE_NIGHT_YES:
                    MainActivity.requestBuilder = GlideApp.with(getBaseContext()).load(TeamLogoURL).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                    break;
                default:
                    MainActivity.requestBuilder = GlideApp.with(getBaseContext()).load(TeamLogoURL).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                    break;
            }


            MainActivity.requestBuilder.into(country_flag);
        } else {
            night_mode.setVisibility(View.INVISIBLE);
            NightMode.setVisibility(View.INVISIBLE);
            country_select.setVisibility(View.INVISIBLE);
            auto_refresh.setVisibility(View.INVISIBLE);
            auto_refresh_mode.setVisibility(View.INVISIBLE);
            no_internet_connection.setVisibility(View.VISIBLE);
            NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    finish();
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);

                }
            });

        }

    }

    public void select_country(View view) {

        final CountryPicker picker = CountryPicker.newInstance();  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String flagURLID) {
                // Implement your code here
                TextView country_name = findViewById(R.id.country_name);
                country_name.setText(name);

                ImageView before = findViewById(R.id.country_flag);

                MainActivity.requestBuilder = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ? GlideApp.with(getBaseContext()).load(flagURLID).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565) : GlideApp.with(getBaseContext()).load(flagURLID).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);

                MainActivity.requestBuilder.into(before);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("country_name", name);
                editor.apply();
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }
}