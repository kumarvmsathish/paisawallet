package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.BaseActivity;
import com.vivartha.kryptopal.adapter.PayAdapter;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Prepaid;
import com.vivartha.kryptopal.utility.ListScrollView;
import com.vivartha.kryptopal.utility.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment {

    private View view;
    private Context mContext;
    private ListView listViewPay;
    /**
     * List of the currency symbol, name, points and currency.
     * These all are static value data.
     */
    private Integer[] currencyTypeImage;
    private String[] currencyName;
    private double[] currencyPoints;
    private int[] currencyValues;

    private PayAdapter payAdapter;
    private ArrayList<Prepaid> arrayList;
    private Prepaid prepaid;
    private SessionManager sessionManager;
    private double dCash;
    private Button btnPayNow;
    private EditText edtPrepaid;
    private double dBalanceCash;
    private String strPayAmount;
    private String strCurDate;
    private DbOperations dbOperation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pay, container, false);
        initUI(view);
        setCurrencyValues();
        setAdapterData();
        getCurrentDate();
        clickListeners();
        return view;
    }


    private void setCurrencyValues() {
        if (sessionManager.getAddAmount() > 0) {
            dCash = sessionManager.getAddAmount();
        } else {
            dCash = 0.00;
        }
        currencyTypeImage = new Integer[]{R.drawable.cash_icon, R.drawable.eth_home_icon, R.drawable.kpx_home_icon, R.drawable.ppx_home_icon};
        currencyName = new String[]{"Cash", "ETH", "KPX", "PPX"};
        currencyPoints = new double[]{0.0, 79.3575, 1920.6574, 100.0000};
        currencyValues = new int[]{(int) dCash, 123500, 11500, 1000};
    }

    // Initialize the UI content where present in the fragment_pay_layout.

    private void initUI(View view) {
        mContext = getActivity();
        dbOperation = new DbOperations(mContext);
        sessionManager = new SessionManager(mContext);
        edtPrepaid = (EditText) view.findViewById(R.id.prepaid_edt_id);
        btnPayNow = (Button) view.findViewById(R.id.pay_now_btn_id);
        listViewPay = (ListView) view.findViewById(R.id.pay_list_id);
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd HH:mm", Locale.ENGLISH);
        strCurDate = df.format(calendar.getTime());
    }

    //get and set the values to the payAdapter and list into the listViewPay.
    private void setAdapterData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < currencyTypeImage.length; i++) {
            prepaid = new Prepaid();
            prepaid.setCurrencyTypeImage(currencyTypeImage[i]);
            prepaid.setCurrencyPoints(currencyPoints[i]);
            prepaid.setCurrencyValues(currencyValues[i]);
            prepaid.setCurrencyName(currencyName[i]);
            arrayList.add(prepaid);
        }
        payAdapter = new PayAdapter(mContext, arrayList);

        listViewPay.setAdapter(payAdapter);
        listViewPay.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        /**
         *  Within Scrollview display all the pay list as listView, For that pass the parameter to
         the setListViewHeightBaseOnChildren.
         */

        ListScrollView.setListViewHeightBasedOnChildren(listViewPay);
    }


    private void clickListeners() {
        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = prepaid.getSelectedPosition();
                strPayAmount = edtPrepaid.getText().toString();

                if (strPayAmount.equals("")) {
                    strPayAmount = "0.00";
                }

                if (0 == position) {
                    double dPrepaidAmount = Double.parseDouble(strPayAmount);
                    if (dCash == 0) {
                        ((BaseActivity) mContext).showAlert(v, "Sorry! No Balance");
                    } else if (dCash < dPrepaidAmount) {
                        ((BaseActivity) mContext).showAlert(v, "Sorry! Your balance is low");
                    } else {
                        if (dbOperation.addTransaction("Paid", currencyName[position], strPayAmount, strCurDate)) {

                            dBalanceCash = dCash - dPrepaidAmount;
                            sessionManager.setAddAmount(dBalanceCash);
                            ((BaseActivity) mContext).showSuccessAlertDialogue(mContext, "Amount paid successful");
                        }
//                        if (dbOperation.addPrepaidAmount(currencyName[position], strPayAmount, strCurDate)) {
//                            dBalanceCash = dCash - dPrepaidAmount;
//
//                            sessionManager.setAddAmount(dBalanceCash);
//                            ((BaseActivity) mContext).showSuccessAlertDialogue(mContext, "Amount paid successful");
//                        }
                    }
                } else {
                    ((BaseActivity) mContext).showSuccessAlertDialogue(mContext, "Amount paid successful");
                }

            }
        });
    }


}
