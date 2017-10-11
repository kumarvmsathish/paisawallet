package com.vivartha.kryptopal.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.fragment.AddMoneyFragment;
import com.vivartha.kryptopal.fragment.BalanceFragment;
import com.vivartha.kryptopal.fragment.HomeFragment;
import com.vivartha.kryptopal.fragment.PayFragment;
import com.vivartha.kryptopal.fragment.ProfileFragment;
import com.vivartha.kryptopal.interfaces.ChangeNotify;

public class HomeActivity extends BaseActivity implements ChangeNotify {

    public static final int HOME_FRAGMENT = 0;

    public static final int BALANCE_FRAGMENT = HOME_FRAGMENT + 1;

    public static final int PAY_FRAGMENT = BALANCE_FRAGMENT + 1;

    public static final int ADD_MONEY_FRAGMENT = PAY_FRAGMENT + 1;

    public static final int PROFILE_FRAGMENT = ADD_MONEY_FRAGMENT + 1;

    public static final int FRAGMENT_COUNT = PROFILE_FRAGMENT + 1;


    private Fragment fragment[];
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Context context;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    boolean doubleBackToExitPressedOnce = false;

    /**
     * menuId for Check navigation bar to
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_home_screen);

        initUI();
        initFragments();
    }

    //Initialize all the UI components which is in activity_home_screen layout.
    private void initUI() {
        context = HomeActivity.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    // Initialize fragments where where keeping in HomeActivity Screen.
    private void initFragments() {
        fragment = new Fragment[FRAGMENT_COUNT];
        fragment[HOME_FRAGMENT] = new HomeFragment();
        fragment[BALANCE_FRAGMENT] = new BalanceFragment();
        fragment[PAY_FRAGMENT] = new PayFragment();
        fragment[ADD_MONEY_FRAGMENT] = new AddMoneyFragment();
        fragment[PROFILE_FRAGMENT] = new ProfileFragment();
        // Show HomeFragment after initialize done.
        showHomeFragment();
    }

    /**
     * In showHomeFragment present all the UI elements of PaisaWallet, From there user can navigate to different screen
     */
    private void showHomeFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_screen_content_id, fragment[HOME_FRAGMENT]);
        ((HomeFragment) fragment[HOME_FRAGMENT]).setChangeNotifyListener(this);
        fragmentTransaction.commit();
    }

    /**
     * onBackPress it will show the snack bar like " Please click back again to exit! "
     * it will quit the application continuous double back press.
     */
    @Override
    public void onBackPressed() {
        if (fragment[HOME_FRAGMENT].isVisible()) {

            if (doubleBackToExitPressedOnce) {

                super.onBackPressed();
                return;

            }
            this.doubleBackToExitPressedOnce = true;

            // Show the snack bar with message while back press
            ((BaseActivity) context).showAlert(coordinatorLayout, "Please click back again to exit!");

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
//
        } else {
            showHomeFragment();
        }
    }


    /**
     * change the fragment screens
     * Through this, change one fragment screen to another fragment screen by using position.
     *
     * @param pos
     */

    @Override
    public void changeFragment(int pos) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_screen_content_id, fragment[pos]);
        fragmentTransaction.commit();
    }
}
