package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.adapter.TransactionPager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionsFragment extends Fragment implements TabLayout.OnTabSelectedListener {


    private View view;
    private TabLayout tabLayoutTransaction;
    private ViewPager viewPagerTransaction;
    private Context mContext;
    private TransactionPager transactionPager;
    private TextView txtCurrentDate;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transactions, container, false);
        initUI();
        setCurrentDate();
        setTabValues();
        return view;
    }

    private void setCurrentDate() {
        txtCurrentDate.setText(simpleDateFormat.format(calendar.getTime()));
    }

    /**
     * Add the tabLayout for All the transaction view like all, sent, Received and Transferred
     * After that set it into the viewPager for swipe
     * While Tab selecting for changing the viewPager used method :addOnTabSelectedListener
     * While Page swiping, for changing the tab used method :addOnPageChangeListener
     */
    private void setTabValues() {
        tabLayoutTransaction.addTab(tabLayoutTransaction.newTab().setText("All"));
        tabLayoutTransaction.addTab(tabLayoutTransaction.newTab().setText("Sent"));
        tabLayoutTransaction.addTab(tabLayoutTransaction.newTab().setText("Received"));
        tabLayoutTransaction.addTab(tabLayoutTransaction.newTab().setText("Transferred"));
        tabLayoutTransaction.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayoutTransaction.addOnTabSelectedListener(this);
        transactionPager = new TransactionPager(getChildFragmentManager(), tabLayoutTransaction.getTabCount());
        viewPagerTransaction.setAdapter(transactionPager);
        viewPagerTransaction.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutTransaction));
    }

    // Initialize the UI Components belongs to the fragment_transactions
    private void initUI() {
        mContext = getActivity();
        simpleDateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        calendar = Calendar.getInstance();
        txtCurrentDate = (TextView) view.findViewById(R.id.current_date_txt_id);
        tabLayoutTransaction = (TabLayout) view.findViewById(R.id.transaction_tab_id);
        viewPagerTransaction = (ViewPager) view.findViewById(R.id.transaction_view_pager_id);
    }

    /**
     * Select the current position for tab use setCurrent position
     *
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPagerTransaction.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
