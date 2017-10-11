package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;
import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.adapter.ValuesAdapter;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Values;
import com.vivartha.kryptopal.utility.API;
import com.vivartha.kryptopal.utility.HttpProcessor;
import com.vivartha.kryptopal.utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValuesFragment extends Fragment implements HttpProcessor.HttpResponser {

    private View view;
    private Context mContext;
    private ListView listViewValues;
    private double currencyCount;
    private double percentHourChanges;
    private DbOperations dbOperations;
    //    private Integer[] currencyTypeImage = {R.drawable.btc_icon, R.drawable.eth_icon, R.drawable.kpx_icon, R.drawable.ppx_icon};
//    private String[] currencyName = {"BTC", "ETH", "KPX", "PPX"};
//    private double[] currencyPoints = {5.1075, 79.3575, 1920.6574, 100.0000};
//    private int[] currencyValues = {21345, 12323500, 11500, 1000};
//    private Integer[] currencyStatus = {R.drawable.up_arrow, R.drawable.up_arrow, R.drawable.up_arrow, R.drawable.down_arrow};
    private ValuesAdapter valuesAdapter;
    private ArrayList<Values> arrayList;
    private Values values;
    private TextView txtCurrencyValue;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_values, container, false);
        initUI(view);
        getCurrencyValues();
        return view;
    }


    /**
     * Getting currency values from the server.
     * which gives all the details of value changes by hours, day, week basis.
     * Request as GET Method for get the currency details.
     */
    private void getCurrencyValues() {
        RequestBody requestBody = new FormEncodingBuilder()
                .build();
        HttpProcessor httpProcessor = new HttpProcessor(mContext, true, API.BASE_URL + "" + API.EXCHANGE, HttpProcessor.GET, requestBody);
        httpProcessor.executeRequest(API.EXCHANGE);
        httpProcessor.setHttpResponserListener(this);
    }

    /**
     * Initialize all the UI components presents in the fragment_values.
     *
     * @param view
     */
    private void initUI(View view) {
        mContext = getActivity();
        dbOperations = new DbOperations(mContext);
        sessionManager = new SessionManager(mContext);
        txtCurrencyValue = (TextView) view.findViewById(R.id.total_currency_value_txt_id);
        listViewValues = (ListView) view.findViewById(R.id.values_list_id);
    }

//    private void setAdapterData() {
//        arrayList = new ArrayList<>();
//        for (int i = 0; i < currencyTypeImage.length; i++) {
//            values = new Values();
//            values.setCurrencyTypeImage(currencyTypeImage[i]);
//            values.setCurrencyPoints(currencyPoints[i]);
//            values.setCurrencyValues(currencyValues[i]);
//            values.setCurrencyStatus(currencyStatus[i]);
//            values.setCurrencyName(currencyName[i]);
//            arrayList.add(values);
//        }
//        valuesAdapter = new ValuesAdapter(mContext, arrayList);
//        listViewValues.setAdapter(valuesAdapter);
//    }


    /**
     * @param result
     * @param TAG    Getting Response from the server as Json object format
     *               All the values setting into the Value model and pass into the ValueAdapter for populating the data.
     */
    @Override
    public void responseResult(JSONObject result, String TAG) {
        if (API.EXCHANGE.equals(TAG)) {
            JSONArray jsonArray = result.optJSONArray("rows");
            arrayList = new ArrayList<>();
            currencyCount = 0;
            percentHourChanges = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                values = new Values();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                values.setId(jsonObject.optString("id"));
                values.setCurrencyName(jsonObject.optString("name"));
                values.setCurrencyValues(jsonObject.optDouble("price_usd"));
                values.setCurrencySymbol(jsonObject.optString("symbol"));
                values.setCurrencyPoints(jsonObject.optDouble("percent_change_1h"));
                arrayList.add(values);
                currencyCount = currencyCount + jsonObject.optDouble("price_usd");
                percentHourChanges = percentHourChanges + jsonObject.optDouble("percent_change_1h");
                dbOperations.addValues(values);
            }

            valuesAdapter = new ValuesAdapter(mContext, arrayList);
            listViewValues.setAdapter(valuesAdapter);
            /**
             * Calculate the total and show with US symbol
             */
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
            numberFormat.setMaximumFractionDigits(2);
            txtCurrencyValue.setText(String.valueOf(numberFormat.format(currencyCount)));
            sessionManager.setKryptoBalance(currencyCount);
            sessionManager.setPercentHourChanges(percentHourChanges);

        }

    }
}
