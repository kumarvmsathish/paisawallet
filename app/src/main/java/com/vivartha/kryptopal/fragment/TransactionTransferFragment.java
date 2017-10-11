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
public class TransactionTransferFragment extends Fragment {


    private static TransactionTransferFragment transactionTransferFragment;
    private View view;

    public static TransactionTransferFragment getInstance() {
        if (null == transactionTransferFragment) {
            transactionTransferFragment = new TransactionTransferFragment();
        }

        return transactionTransferFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tansaction_transfer, container, false);

        return view;
    }

}
