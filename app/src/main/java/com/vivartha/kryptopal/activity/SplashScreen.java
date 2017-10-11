package com.vivartha.kryptopal.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.vivartha.kryptopal.R;

public class SplashScreen extends AppCompatActivity {
    // Delay time is 3 seconds(3000)
    private static final int DELAY_TIMES = 3000;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for full screen with without status bar and and title bar.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        pageRedirection();
        context = this;
    }

    /**
     * Which handle the screen delay time and page redirection
     */
    private void pageRedirection() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startIntent();
            }
        }, DELAY_TIMES);
    }

    // Start action to HomeActivity
    private void startIntent() {
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
