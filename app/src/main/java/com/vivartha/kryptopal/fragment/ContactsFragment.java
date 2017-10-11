package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.adapter.ContactsAdapter;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Contact;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Context context;
    private ListView listContacts;
    private ContactsAdapter contactsAdapter;
    private ArrayList<Contact> arrayList;
    private Contact contact;
    private DbOperations dbOperations;
    private ImageView imgAddContact;
    /**
     * Static content for showing contents in contact list
     */
    private Integer[] personImage = {R.drawable.contact_psn_1_image, R.drawable.contact_psn_2_image, R.drawable.contact_psn_3_image,
            R.drawable.contact_psn_4_image, R.drawable.contact_psn_5_image, R.drawable.contact_psn_6_image,};
    private String[] personName = {"Aarun Samuel", "Arun Giri", "Dolendro", "John Doe", "Mike Rhodes", "Samantha"};
    private String[] addedDate = {"June 5th, 2017", "June 3th, 2017", "May 21th, 2017", "Augest 17th, 2017", "April 5th, 2017", "December 14th, 2017"};
    private String[] country = {"India", "USA", "UK", "India", "USA", "UK"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contacts, container, false);
        initUI(view);
        setAdapterData();
        clickListeners();
        return view;
    }

    private void clickListeners() {
        imgAddContact.setOnClickListener(this);
    }

    // Initialize the UI Components belongs to the fragment_contacts
    private void initUI(View view) {
        context = getActivity();
        imgAddContact = (ImageView) view.findViewById(R.id.add_contact_id);
        dbOperations = new DbOperations(context);
        listContacts = (ListView) view.findViewById(R.id.contact_list_id);
    }

    // Set all the static values into the arrayList
    private void setAdapterData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < personName.length; i++) {
            contact = new Contact();
            contact.setPersonImage(personImage[i]);
            contact.setPersonName(personName[i]);
            contact.setAddedDate(addedDate[i]);
            contact.setCountry(country[i]);
            arrayList.add(contact);

            dbOperations.addContacts(contact);

        }
        /**
         * Set all static values to adapter and set that adapter into the listView
         */
        contactsAdapter = new ContactsAdapter(context, arrayList);
        listContacts.setAdapter(contactsAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.add_contact_id) {
            // Creates a new Intent to insert a contact
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
// Sets the MIME type to match the Contacts Provider
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            startActivity(intent);
        }
    }
}
