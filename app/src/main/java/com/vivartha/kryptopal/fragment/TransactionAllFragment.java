package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.adapter.TransactionAdapter;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionAllFragment extends Fragment {

    private static TransactionAllFragment transactionAllFragment;
    private Transaction transaction;
    private List<Transaction> arrayList;
    private DbOperations dbOperations;
    private View view;
    private ListView transactionAllList;
    private TransactionAdapter transactionAdapter;
    private Context mContext;
    private TextView txtNoTransaction;
    private Integer[] transactionImage = {R.drawable.sent_bitcoin_icon, R.drawable.transfer_paisapay_icon, R.drawable.receive_bitcoin_icon,
            R.drawable.sent_ether_icon};
    private String[] transactionName = {"To : Aaron Samuel", "To : FIAT Wallet", "From : Samuel", "To : Arun"};
    private String[] transactionTitle = {"Sent Bitcoins", "Transferred to PaisaPay", "Received Ether", "Sent Ether"};
    private String[] transactionDate = {"August 24, 13:34", "August 13, 19:01", "August 4, 11:32", "August 2, 17:31"};
    //    private String[] transactionAddress = {""};
//    private Integer[] transactionArrow = {R.drawable.up_arrow, R.drawable.up_arrow, R.drawable.up_arrow, R.drawable.up_arrow};
    private int[] transactionAmount = {500, 1000, 750, 600};


    public static TransactionAllFragment getInstance() {
        if (null == transactionAllFragment) {
            transactionAllFragment = new TransactionAllFragment();
        }

        return transactionAllFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transaction_all, container, false);
        initUI(view);
        setAdapterData();
        return view;
    }

    private void setAdapterData() {
//        arrayList = new ArrayList<>();
        arrayList = dbOperations.getAllTransactions();
//        for (int i = 0; i < transactionTitle.length; i++) {
//            transaction = new Transaction();
//            transaction.setTransactionImage(transactionImage[i]);
//            transaction.setTransactionTitle(transactionTitle[i]);
//            transaction.setTransactionName(transactionName[i]);
//            transaction.setTransactionDate(transactionDate[i]);
////            transaction.setTransactionAmount(transactionAmount[i]);
//            arrayList.add(transaction);
//        }

        if (arrayList.size() <= 0) {
            txtNoTransaction.setVisibility(View.VISIBLE);
        } else {
            transactionAdapter = new TransactionAdapter(mContext, arrayList);
            transactionAllList.setAdapter(transactionAdapter);
            txtNoTransaction.setVisibility(View.GONE);
        }

    }

    private void initUI(View view) {
        mContext = getActivity();
        dbOperations = new DbOperations(mContext);
        txtNoTransaction = (TextView) view.findViewById(R.id.no_transaction_txt_id);
        transactionAllList = (ListView) view.findViewById(R.id.transaction_all_list_id);
    }

}
