package com.vivartha.kryptopal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.model.Prepaid;
import com.vivartha.kryptopal.model.Rewards;
import com.vivartha.kryptopal.model.Values;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ANANTH on 19-09-2017.
 */

public class PayAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private View view;
    private ArrayList<Prepaid> arrayList;
    private Prepaid prepaid;
    private int selectedPosition = 0;


    public PayAdapter(Context mContext, ArrayList<Prepaid> arrayList) {
        this.context = mContext;
        this.arrayList = arrayList;
        // Get the system inflation service for the inflating the layout views.
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        prepaid = arrayList.get(position);
        view = convertView;
        /**
         * If view is null the inflate the layout and set it into the viewHolder by using setTag.
         * Else it will get the views from the getTag and keep it into the viewHolder.
         */
        if (null == view) {
            view = layoutInflater.inflate(R.layout.pay_content_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        /**
         * Set the US Currency format with Symbol
         * setMinimumFractionDigits(4) for 4 decimal values
         */
        DecimalFormat format=new DecimalFormat("##,##,###.##");

        /**
         * Set all the data into the viewHolder views to populate.
         */

        viewHolder.imgCurrencyType.setImageResource(prepaid.getCurrencyTypeImage());

        viewHolder.txtCurrencyValues.setText(String.valueOf(format.format(prepaid.getCurrencyValues())));
        viewHolder.txtCurrencyName.setText(prepaid.getCurrencyName());
        viewHolder.radioCheckItem.setChecked(position == selectedPosition);
        viewHolder.radioCheckItem.setTag(position);
        /**
         * List with radio button, For single select
         */
        if (position == 0) {
            viewHolder.txtCurrencyPoints.setText("- - - - - -");
        } else {
            viewHolder.txtCurrencyPoints.setText(String.valueOf(prepaid.getCurrencyPoints()));
        }
        viewHolder.radioCheckItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = (int) v.getTag();
                prepaid.setSelectedPosition(selectedPosition);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    // Check it whether radio button checked or not
    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
//        Toast.makeText(context, "position : " + selectedPosition, Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for holding the views, which presents in the values_content_layout.xml layout
     */

    private class ViewHolder {

        private ImageView imgCurrencyType;
        private TextView txtCurrencyPoints;
        private TextView txtCurrencyValues;
        private TextView txtCurrencyName;
        private RadioButton radioCheckItem;

        public ViewHolder(View view) {
            radioCheckItem = (RadioButton) view.findViewById(R.id.set_checked_item_id);
            imgCurrencyType = (ImageView) view.findViewById(R.id.img_currency_type_id);
            txtCurrencyName = (TextView) view.findViewById(R.id.txt_currency_name_id);
            txtCurrencyPoints = (TextView) view.findViewById(R.id.txt_currency_point_id);
            txtCurrencyValues = (TextView) view.findViewById(R.id.txt_currency_value_id);
        }
    }
}
