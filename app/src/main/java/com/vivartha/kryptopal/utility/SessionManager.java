package com.vivartha.kryptopal.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class SessionManager {

    private Context context;
    private SharedPreferences pref;
    private Editor editor;
    private String PREFERENCE_NAME = "KryptoPal";
    private int MODE_PRIVATE = 0;

    private static final String KEY_ADD_AMOUNT = "ADD_AMOUNT";
    private static final String KEY_KRYPTO_BALANCE = "KRYPTO_BALANCE";
    private static final String KEY_HOUR_PERCENT_CHANGES = "HOUR_PERCENT_CHANGES";

    public SessionManager(Context context) {
        // TODO Auto-generated constructor stub

        this.context = context;
        pref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        editor = pref.edit();
    }


    public void setAddAmount(double dAddedAmount) {
        editor.putFloat(KEY_ADD_AMOUNT, (float) dAddedAmount);
        editor.commit();
    }

    public double getAddAmount() {
        return pref.getFloat(KEY_ADD_AMOUNT, 0);

    }

    public void setKryptoBalance(double currencyCount) {
        editor.putFloat(KEY_KRYPTO_BALANCE, (float) currencyCount);
        editor.commit();
    }

    public double getKryptoBalance() {
        return pref.getFloat(KEY_KRYPTO_BALANCE, 0);
    }

    public void setPercentHourChanges(double percent) {
        editor.putFloat(KEY_HOUR_PERCENT_CHANGES, (float) percent);
        editor.commit();
    }

    public double getHourPercent() {
        return pref.getFloat(KEY_HOUR_PERCENT_CHANGES, 0);
    }
}

