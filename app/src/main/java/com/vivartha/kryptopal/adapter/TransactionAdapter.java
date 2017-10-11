package com.vivartha.kryptopal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANANTH on 20-09-2017.
 */

public class TransactionAdapter extends BaseAdapter {
    private View view;
    private List<Transaction> arrayList;
    private LayoutInflater layoutInflater;
    private Transaction transaction;

    public TransactionAdapter(Context mContext, List<Transaction> arrayList) {
        this.arrayList = arrayList;
        // Get the system inflation service for the inflating the layout views.
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        view = convertView;
        transaction = arrayList.get(position);
        /**
         * If view is null the inflate the layout and set it into the viewHolder by using setTag.
         * Else it will get the views from the getTag and keep it into the viewHolder.
         */
        if (null == view) {
            view = layoutInflater.inflate(R.layout.transaction_content_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        /**
         * Set all the data into the viewHolder views to populate.
         */
        if ("Added".equals(transaction.getTransactionTitle())) {
            viewHolder.transactionImage.setImageResource(R.drawable.sent_ether_icon);
        } else if ("Paid".equals(transaction.getTransactionTitle())) {
            viewHolder.transactionImage.setImageResource(R.drawable.receive_bitcoin_icon);
        }
        viewHolder.transactionName.setText("From : " + transaction.getTransactionName());
        viewHolder.transactionAmount.setText(String.valueOf(transaction.getTransactionAmount()));
        viewHolder.transactionTitle.setText(transaction.getTransactionTitle());

        viewHolder.transactionDate.setText(transaction.getTransactionDate());

        return view;
    }

    /**
     * ViewHolder class for holding the views, which presents in the values_content_layout.xml layout
     */
    private class ViewHolder {
        private ImageView transactionImage;
        private TextView transactionAmount;
        private TextView transactionTitle;
        private TextView transactionName;
        private TextView transactionDate;

        private ViewHolder(View view) {
            transactionImage = (ImageView) view.findViewById(R.id.transaction_image_id);
            transactionAmount = (TextView) view.findViewById(R.id.transaction_amount_id);
            transactionTitle = (TextView) view.findViewById(R.id.transaction_title_id);
            transactionName = (TextView) view.findViewById(R.id.transaction_name_id);
            transactionDate = (TextView) view.findViewById(R.id.transaction_date_id);
        }
    }
}
