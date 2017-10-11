package com.vivartha.kryptopal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Banks;
import com.vivartha.kryptopal.model.Values;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ANANTH on 19-09-2017.
 */

public class AddMoneyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private View view;
    private int selectedPosition = -1;
    private List<Banks> banksList;
    private Banks banks;

    public AddMoneyAdapter(Context mContext, List<Banks> banksList) {
        this.context = mContext;
        this.banksList = banksList;
        // Get the system inflation service for the inflating the layout views.
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        selectedPosition = -1;
    }

    @Override
    public int getCount() {
        return banksList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        view = convertView;
        banks = banksList.get(position);
        /**
         * If view is null the inflate the layout and set it into the viewHolder by using setTag.
         * Else it will get the views from the getTag and keep it into the viewHolder.
         */
        if (null == view) {
            view = layoutInflater.inflate(R.layout.add_money_content_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        /**
         * Set all the data into the viewHolder views to populate.
         */

        viewHolder.imgBanksList.setImageResource(banks.getImgBanksLogo());
        if (selectedPosition == position) {
            banks.setBankPosition(selectedPosition);
            viewHolder.layoutBackground.setBackgroundResource(R.drawable.item_pressed);
        } else {
            banks.setBankPosition(selectedPosition);
            viewHolder.layoutBackground.setBackgroundResource(R.drawable.item_normal);
        }
        viewHolder.layoutBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        return view;
    }

    /**
     * ViewHolder class for holding the views, which presents in the values_content_layout.xml layout
     */

    private class ViewHolder {

        private ImageView imgBanksList;
        private LinearLayout layoutBackground;


        private ViewHolder(View view) {
            layoutBackground = (LinearLayout) view.findViewById(R.id.rectangle_background_id);
            imgBanksList = (ImageView) view.findViewById(R.id.bank_name_id);
        }
    }
}
