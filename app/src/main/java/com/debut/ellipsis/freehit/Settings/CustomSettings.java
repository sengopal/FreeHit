package com.debut.ellipsis.freehit.Settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.CountryHash;
import com.debut.ellipsis.freehit.IntoSlider.CountryPicker;
import com.debut.ellipsis.freehit.IntoSlider.CountryPickerListener;
import com.debut.ellipsis.freehit.R;

import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

public class CustomSettings extends AppCompatActivity {
    public static final String LOG_TAG = CustomSettings.class.getSimpleName();
    private Toolbar toolbar;
    CountryHash countryHash = new CountryHash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ImageView country_flag = (ImageView) findViewById(R.id.country_flag);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("country_name", "Select Your Favourite Country");

        TextView country_name = (TextView) findViewById(R.id.country_name);
        country_name.setText(name);

        country_flag.setImageResource(countryHash.getCountryFlag(name.toUpperCase()));



    }

    public void select_country(View view) {

        final CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
                TextView country_name = (TextView) findViewById(R.id.country_name);
                country_name.setText(name);

                ImageView before = (ImageView) findViewById(R.id.country_flag);
                before.setImageResource(flagDrawableResID);

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("country_name", name);
                editor.apply();
                picker.dismiss();

            }
        });
        picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");

    }

    
}