package com.vivartha.kryptopal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vivartha.kryptopal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionReceiveFragment extends Fragment {


    private static TransactionReceiveFragment transactionReceiveFragment;
    private View view;

    public static TransactionReceiveFragment getInstance() {
        if (null == transactionReceiveFragment) {
            transactionReceiveFragment = new TransactionReceiveFragment();
        }

        return transactionReceiveFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_receive, container, false);

        return view;
    }

}
