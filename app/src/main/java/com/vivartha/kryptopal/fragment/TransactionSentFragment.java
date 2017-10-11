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
public class TransactionSentFragment extends Fragment {

    private static TransactionSentFragment transactionSentFragment;
    private View view;

    public static TransactionSentFragment getInstance() {
        if (null == transactionSentFragment) {
            transactionSentFragment = new TransactionSentFragment();
        }

        return transactionSentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_sent, container, false);

        return view;
    }

}
