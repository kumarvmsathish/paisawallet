package com.vivartha.kryptopal.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.fragment.BuyFragment;
import com.vivartha.kryptopal.fragment.CardsFragment;
import com.vivartha.kryptopal.fragment.ContactsFragment;
import com.vivartha.kryptopal.fragment.ExchangeFragment;
import com.vivartha.kryptopal.fragment.IdentityFragment;
import com.vivartha.kryptopal.fragment.KryptoFragment;
import com.vivartha.kryptopal.fragment.RequestFragment;
import com.vivartha.kryptopal.fragment.RewardsFragment;
import com.vivartha.kryptopal.fragment.SellFragment;
import com.vivartha.kryptopal.fragment.SendFragment;
import com.vivartha.kryptopal.fragment.TransactionsFragment;
import com.vivartha.kryptopal.fragment.TransferFragment;
import com.vivartha.kryptopal.fragment.ValuesFragment;
import com.vivartha.kryptopal.interfaces.ChangeNotify;

public class KryptoActivity extends BaseActivity implements ChangeNotify, BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    /**
     * Integer value for the krypto activity contents,
     * Keep the int array values to the fragment position.
     */
    public static final int KRYPTO_FRAGMENT = 0;

    public static final int IDENTITY_FRAGMENT = KRYPTO_FRAGMENT + 1;

    public static final int VALUE_FRAGMENT = IDENTITY_FRAGMENT + 1;

    public static final int CONTACT_FRAGMENT = VALUE_FRAGMENT + 1;

    public static final int REWARDS_FRAGMENT = CONTACT_FRAGMENT + 1;

    public static final int CARDS_FRAGMENT = REWARDS_FRAGMENT + 1;

    public static final int TRANSACTION_FRAGMENT = CARDS_FRAGMENT + 1;

    public static final int EXCHANGE_FRAGMENT = TRANSACTION_FRAGMENT + 1;

    public static final int SEND_FRAGMENT = EXCHANGE_FRAGMENT + 1;

    public static final int REQUEST_FRAGMENT = SEND_FRAGMENT + 1;

    public static final int TRANSFER_FRAGMENT = REQUEST_FRAGMENT + 1;

    public static final int SELL_FRAGMENT = TRANSFER_FRAGMENT + 1;

    public static final int BUY_FRAGMENT = SELL_FRAGMENT + 1;

    public static final int FRAGMENT_COUNT = BUY_FRAGMENT + 1;

    private Fragment fragment[];
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krypto);

        initUI();
        setToolBarValues();
        initFragments();
    }

    // Initialize the fragments all presents in the krypto activity.
    private void initFragments() {
        fragment = new Fragment[FRAGMENT_COUNT];
        fragment[KRYPTO_FRAGMENT] = new KryptoFragment();
        fragment[IDENTITY_FRAGMENT] = new IdentityFragment();
        fragment[VALUE_FRAGMENT] = new ValuesFragment();
        fragment[CONTACT_FRAGMENT] = new ContactsFragment();
        fragment[REWARDS_FRAGMENT] = new RewardsFragment();
        fragment[CARDS_FRAGMENT] = new CardsFragment();
        fragment[TRANSACTION_FRAGMENT] = new TransactionsFragment();
        fragment[EXCHANGE_FRAGMENT] = new ExchangeFragment();
        fragment[SEND_FRAGMENT] = new SendFragment();
        fragment[REQUEST_FRAGMENT] = new RequestFragment();
        fragment[TRANSFER_FRAGMENT] = new TransferFragment();
        fragment[SELL_FRAGMENT] = new SellFragment();
        fragment[BUY_FRAGMENT] = new BuyFragment();

        showKryptoFragment();
    }

    // Show kryptofragment to the kryptoActivity
    private void showKryptoFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.krypto_home_content, fragment[KRYPTO_FRAGMENT]);
        ((KryptoFragment) fragment[KRYPTO_FRAGMENT]).setChangeNotifyListener(this);

        fragmentTransaction.commit();
    }

    /**
     * Set the back icon with back pressed in toolbar
     * For that make it setDisplayHomeAsUpEnabled(true)
     */
    private void setToolBarValues() {
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

    // Initialize the UI elements which presents in activity_krypto layout.
    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.krypto_home_tool_id);
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
        fragmentTransaction.replace(R.id.krypto_home_content, fragment[pos]);
        fragmentTransaction.commit();
    }

    /**
     * onBackPress will work on back press.
     * if Visible any other fragment instead of krypto fragment, then it will show krypto fragment while back press.
     * Else it will quit the krypto activity and go back to home activity screen.
     */
    @Override
    public void onBackPressed() {
        if (fragment[KRYPTO_FRAGMENT].isVisible()) {
            super.onBackPressed();
        } else {
            showKryptoFragment();
        }
    }

    // For Bottom view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.bottom_krypto_home_id:
                showKryptoFragment();
                break;
        }

        return true;
    }
}
