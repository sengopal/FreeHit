package com.debut.ellipsis.freehit.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.OnClearFromRecentService;


public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
        finish();
    }
}
