package com.vivartha.kryptopal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vivartha.kryptopal.fragment.TransactionAllFragment;
import com.vivartha.kryptopal.fragment.TransactionReceiveFragment;
import com.vivartha.kryptopal.fragment.TransactionSentFragment;
import com.vivartha.kryptopal.fragment.TransactionTransferFragment;

/**
 * Created by ANANTH on 20-09-2017.
 */

public class TransactionPager extends FragmentPagerAdapter {

    private int tabCount;
    private TransactionAllFragment transactionAllFragment;
    private TransactionSentFragment transactionSentFragment;
    private TransactionReceiveFragment transactionReceiveFragment;
    private TransactionTransferFragment transactionTransferFragment;


    public TransactionPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
/**
 * Replacing the TransactionAllFragment,TransactionSentFragment,TransactionReceiveFragment,
 TransactionTransferFragment with View pager and tabLayout
 */
            case 0:
                transactionAllFragment = TransactionAllFragment.getInstance();
                return transactionAllFragment;
            case 1:
                transactionSentFragment = TransactionSentFragment.getInstance();
                return transactionSentFragment;
            case 2:
                transactionReceiveFragment = TransactionReceiveFragment.getInstance();
                return transactionReceiveFragment;
            case 3:
                transactionTransferFragment = TransactionTransferFragment.getInstance();
                return transactionTransferFragment;
            default:
                return null;

        }

    }

    // Return the count of the tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
