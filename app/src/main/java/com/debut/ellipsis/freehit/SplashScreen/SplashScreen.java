package com.debut.ellipsis.freehit.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity;
import com.debut.ellipsis.freehit.R;


public class SplashScreen extends Activity {

    private static int SPLASH_TIMER = 500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMER);


    }
}
