package com.vivartha.kryptopal.utility;

import android.app.Application;

/**
 * Created by ANANTH on 25-12-2016.
 */

public class FontStyle extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Fonts/comfortaa_bold.ttf");
    }
}
