package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.BaseActivity;
import com.vivartha.kryptopal.adapter.AddMoneyAdapter;
import com.vivartha.kryptopal.database.DbOperations;
import com.vivartha.kryptopal.model.Balance;
import com.vivartha.kryptopal.model.Banks;
import com.vivartha.kryptopal.utility.GridScrollView;
import com.vivartha.kryptopal.utility.SessionManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment {

    private View view;
    private EditText edtAddMoney;
    private GridView gridBankList;
    private AddMoneyAdapter addMoneyAdapter;
    private Context mContext;
    private Button btnAddAmount;
    private double dAddedAmount;
    private String strAddAmount;
    private SessionManager sessionManager;
    private String[] bankNames = {"Axis Bank", "HDFC Bank", "ICICI Bank", "Punjob National Bank", "Kotak",
            "Andhra Bank", "Union Bank", "Yes Bank", "Allahabad Bank"};
    private Integer[] banksIcon = {R.drawable.axis_icon, R.drawable.hdfc_icon, R.drawable.icici_icon, R.drawable.punjab_national_icon,
            R.drawable.kotak_icon, R.drawable.andhra_bank_icon, R.drawable.union_bank_icon, R.drawable.yes_bank_icon,
            R.drawable.allahabad_bank_icon};
    private List<Banks> banksList;
    private DbOperations dbOperations;
    private Banks banks;
    private String strAddDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_money, container, false);

        initUI(view);
        setAdapterData();
        getCurrentDate();
        clickListener();

        return view;
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd HH:mm", Locale.ENGLISH);
        strAddDate = df.format(calendar.getTime());
    }

    //Initialize the UI which presents in fragment_add_money
    private void initUI(View view) {
        mContext = getActivity();
        dbOperations = new DbOperations(mContext);
        sessionManager = new SessionManager(mContext);
        btnAddAmount = (Button) view.findViewById(R.id.add_amount_btn_id);
        gridBankList = (GridView) view.findViewById(R.id.banks_list_id);
        edtAddMoney = (EditText) view.findViewById(R.id.add_money_edt_id);
    }

    // Get the value to the adapter and set into the listView
    private void setAdapterData() {
        banksList = new ArrayList<>();
        for (int i = 0; i < bankNames.length; i++) {
            banks = new Banks();
            banks.setImgBanksLogo(banksIcon[i]);
            banks.setStrBanks(bankNames[i]);
            banksList.add(banks);
            dbOperations.addBanks(banks);
        }

        addMoneyAdapter = new AddMoneyAdapter(mContext, banksList);
        gridBankList.setAdapter(addMoneyAdapter);
        GridScrollView.setGridViewHeightBasedOnChildren(gridBankList, 3);
    }

    private void clickListener() {
        btnAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Get the values from the add money edit text.
                 * When press the add money button it will check whether values available from
                 the session manager.
                 *If Values is 0 then it will store directly
                 * Else it will get value from session manager and add with balance
                 */
                int position = banks.getBankPosition();
                strAddAmount = edtAddMoney.getText().toString();

                if (strAddAmount.equals("0.00") || strAddAmount.isEmpty()) {
                    ((BaseActivity) mContext).showAlert(v, "Please add the amount!");
                } else if (position < 0) {
                    ((BaseActivity) mContext).showAlert(v, "Please choose the bank!");
                } else {

                    if (dbOperations.addTransaction("Added", bankNames[position], strAddAmount, strAddDate)) {
                        dAddedAmount = Double.parseDouble(strAddAmount);
                        if (sessionManager.getAddAmount() == 0) {
                            double number = Double.valueOf(dAddedAmount).longValue();
                            sessionManager.setAddAmount(number);
                        } else {
                            sessionManager.setAddAmount(sessionManager.getAddAmount() + dAddedAmount);
                        }
                        ((BaseActivity) mContext).showSuccessAlertDialogue(mContext, "Amount added successfully!");
                    }

//                    if (dbOperations.addMoney(bankNames[position], strAddAmount, strAddDate)) {
//                        dAddedAmount = Double.parseDouble(strAddAmount);
//                        if (sessionManager.getAddAmount() == 0) {
//                            long number = Double.valueOf(dAddedAmount).longValue();
//                            sessionManager.setAddAmount(number);
//                        } else {
//                            sessionManager.setAddAmount(sessionManager.getAddAmount() + dAddedAmount);
//                        }
//                        ((BaseActivity) mContext).showSuccessAlertDialogue(mContext, "Amount added successfully!");
//                    }

                }

            }
        });

    }

}
