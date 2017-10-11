package com.vivartha.kryptopal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.model.Contact;

import java.util.ArrayList;

/**
 * Created by ANANTH on 21-09-2017.
 */

public class ContactsAdapter extends BaseAdapter {
    private Contact contact;
    private View view;
    private ArrayList<Contact> arrayList;
    private LayoutInflater layoutInflater;

    public ContactsAdapter(Context context, ArrayList<Contact> arrayList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        view = convertView;
        contact = arrayList.get(position);
        /**
         * If view is null the inflate the layout and set it into the viewHolder by using setTag.
         * Else it will get the views from the getTag and keep it into the viewHolder.
         */
        if (null == view) {
            view = layoutInflater.inflate(R.layout.contact_content_layout, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        /**
         * Set all the data into the viewHolder views to populate.
         */

        viewHolder.imgPerson.setImageResource(contact.getPersonImage());
        viewHolder.txtPersonName.setText(contact.getPersonName());
        viewHolder.txtAddedDate.setText(contact.getAddedDate());
        viewHolder.txtPersonCountry.setText(contact.getCountry());

        return view;
    }

    /**
     * ViewHolder class for holding the views, which presents in the values_content_layout.xml layout
     */

    private class ViewHolder {
        private ImageView imgPerson;
        private TextView txtPersonName;
        private TextView txtPersonCountry;
        private TextView txtAddedDate;

        private ViewHolder(View view) {
            imgPerson = (ImageView) view.findViewById(R.id.contact_image_id);
            txtPersonName = (TextView) view.findViewById(R.id.contact_name_id);
            txtAddedDate = (TextView) view.findViewById(R.id.added_date_id);
            txtPersonCountry = (TextView) view.findViewById(R.id.contact_country_id);

        }
    }
}
