package com.vivartha.kryptopal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.model.Values;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ANANTH on 19-09-2017.
 */

public class ValuesAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private View view;
    private ArrayList<Values> arrayList;
    private Values values;


    public ValuesAdapter(Context mContext, ArrayList<Values> arrayList) {
        this.context = mContext;
        this.arrayList = arrayList;
        // Get the system inflation service for the inflating the layout views.
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //count the size of the arraylist
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
        values = arrayList.get(position);
        view = convertView;
        /**
         * If view is null the inflate the layout and set it into the viewHolder by using setTag.
         * Else it will get the views from the getTag and keep it into the viewHolder.
         */
        if (null == view) {
            view = layoutInflater.inflate(R.layout.values_content_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        /**
         * Set the US Currency format with Symbol
         * setMinimumFractionDigits(4) for 4 decimal values
         */
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        format.setMinimumFractionDigits(4);

        /**
         * Set all the data into the viewHolder views to populate.
         */

//        viewHolder.imgCurrencyType.setImageResource(values.getCurrencyTypeImage());
        viewHolder.txtCurrencyPoints.setText(String.valueOf(values.getCurrencyPoints()));
        viewHolder.txtCurrencyValues.setText(String.valueOf(format.format(values.getCurrencyValues())));
        viewHolder.txtCurrencySymbol.setText(values.getCurrencySymbol());

        /**
         * if currency value is more than 0 means up arrow(green) it will show.
         * Else it will show the down arrow(red).
          */

                if (values.getCurrencyPoints() > 0) {
            viewHolder.imgCurrencyStatus.setImageResource(R.drawable.up_arrow);
        } else {
            viewHolder.imgCurrencyStatus.setImageResource(R.drawable.down_arrow);
        }

        return view;
    }

    /**
     * ViewHolder class for holding the views, which presents in the values_content_layout.xml layout
     */
    class ViewHolder {

        private ImageView imgCurrencyType;
        private TextView txtCurrencyPoints;
        private TextView txtCurrencyValues;
        private ImageView imgCurrencyStatus;
        private TextView txtCurrencySymbol;

        public ViewHolder(View view) {
            imgCurrencyType = (ImageView) view.findViewById(R.id.img_currency_type_id);
            txtCurrencySymbol = (TextView) view.findViewById(R.id.txt_currency_name_id);
            txtCurrencyPoints = (TextView) view.findViewById(R.id.txt_currency_point_id);
            txtCurrencyValues = (TextView) view.findViewById(R.id.txt_currency_value_id);
            imgCurrencyStatus = (ImageView) view.findViewById(R.id.img_currency_status_id);
        }
    }
}
