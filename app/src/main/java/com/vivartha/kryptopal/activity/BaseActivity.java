package com.vivartha.kryptopal.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.vivartha.kryptopal.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by ANANTH on 22-09-2017.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Show the snack bar for showing message to the user
     * @param view
     * @param message
     */
    public void showAlert(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        view = snackbar.getView();
        TextView txtSnackBar = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        txtSnackBar.setTextColor(Color.YELLOW);
        txtSnackBar.setTextSize(16f);
        snackbar.show();

    }

    /**
     * Showing Alert dialogue for success message
     * @param context
     * @param message
     */
    public void showSuccessAlertDialogue(Context context, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success!")
                .setContentText(message)
                .setConfirmText("Ok")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        onBackPressed();
                        sweetAlertDialog.cancel();
                    }
                })
                .show();
    }


}
