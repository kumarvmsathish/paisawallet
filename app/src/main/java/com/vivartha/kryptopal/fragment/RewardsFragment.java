package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.adapter.RewardsAdapter;
import com.vivartha.kryptopal.adapter.ValuesAdapter;
import com.vivartha.kryptopal.model.Rewards;
import com.vivartha.kryptopal.model.Values;
import com.vivartha.kryptopal.utility.ListScrollView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {

    private View view;
    private Context mContext;
    private ListView listViewValues;
    private Button btnConnectFriends;

    /**
     * Static content for showing contents in Rewards list
     */
    private Integer[] currencyTypeImage = {R.drawable.total_icon, R.drawable.kpx_icon, R.drawable.ppx_icon};
    private String[] currencyName = {"Total", "KPX", "PPX"};
    private double[] currencyPoints = {5.1075, 1920.6574, 100.0000};
    private int[] currencyValues = {21345, 11500, 1000};
    private Integer[] currencyStatus = {R.drawable.up_arrow, R.drawable.up_arrow, R.drawable.up_arrow};
    private RewardsAdapter valuesAdapter;
    private ArrayList<Rewards> arrayList;
    private Rewards values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rewards, container, false);
        initUI(view);
        setAdapterData();
        btnClickListners();
        return view;
    }

    private void btnClickListners() {
        btnConnectFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "KryptoPal");
                intent.putExtra(Intent.EXTRA_TEXT, "For Getting rewards, Please do transaction via PaisaWallet.");
                startActivity(Intent.createChooser(intent,"Connect via"));
            }
        });
    }

    // Initialize the UI Components belongs to the fragment_rewards
    private void initUI(View view) {
        mContext = getActivity();
        btnConnectFriends = (Button) view.findViewById(R.id.connect_friends_btn_id);
        listViewValues = (ListView) view.findViewById(R.id.rewards_list_id);
    }

    // Set all the static values into the arrayList
    private void setAdapterData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < currencyTypeImage.length; i++) {
            values = new Rewards();
            values.setCurrencyTypeImage(currencyTypeImage[i]);
            values.setCurrencyPoints(currencyPoints[i]);
            values.setCurrencyValues(currencyValues[i]);
            values.setCurrencyStatus(currencyStatus[i]);
            values.setCurrencyName(currencyName[i]);
            arrayList.add(values);
        }
        /**
         * Set all static values to adapter and set that adapter into the listView
         * For Showing the listView within ScrollView user method:setListViewHeightBasedOnChildren(listView)
         */
        valuesAdapter = new RewardsAdapter(mContext, arrayList);
        listViewValues.setAdapter(valuesAdapter);
        ListScrollView.setListViewHeightBasedOnChildren(listViewValues);
    }

}
