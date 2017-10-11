package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.BaseActivity;
import com.vivartha.kryptopal.database.DbOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {

    private View view;
    private DbOperations dbOperations;
    private Context context;
    private List<String> valuesSymbolList;
    private Spinner spnrCurrencySymbol;
    private Spinner spnrBanksName;
    private ArrayAdapter<String> arrayAdapter;
    private EditText edtCurrencyValue;
    private EditText edtCurrencyQty;
    private List<String> banksList;
    private Button btnBuyNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sell, container, false);
        initUI(view);
        getToken();
        getBanksList();
        clickListeners();
        return view;
    }

    //Initialize all the UI contents where its belongs to fragment_sell
    private void initUI(View view) {
        context = getActivity();
        valuesSymbolList = new ArrayList<>();
        dbOperations = new DbOperations(context);
        spnrBanksName = (Spinner) view.findViewById(R.id.sell_banks_spnr_id);
        edtCurrencyValue = (EditText) view.findViewById(R.id.sell_value_edt_id);
        edtCurrencyQty = (EditText) view.findViewById(R.id.sell_qty_edt_id);
        spnrCurrencySymbol = (Spinner) view.findViewById(R.id.sell_token_spnr_id);
        btnBuyNow=(Button)view.findViewById(R.id.sell_btn_id);
    }

    /**
     * Get the Token/ Coin Symbol from the local database
     * That symbol getting from the value table
     * Then set the values into the spinner like dropdown list.
     */
    private void getToken() {
        valuesSymbolList = dbOperations.getCurrencyValues();
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, valuesSymbolList);
        spnrCurrencySymbol.setAdapter(arrayAdapter);
    }

    private void getBanksList() {
        banksList = dbOperations.getBanks();
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, banksList);
        spnrBanksName.setAdapter(arrayAdapter);
    }


    private void clickListeners() {
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)context).showSuccessAlertDialogue(context,"Sold successful");

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        textWatcher();
    }

    /**
     * Text watching for currency value and currency quantity.
     * For that we have to created the CurrencyTextWatcher class
     */


    private void textWatcher() {
        edtCurrencyValue.addTextChangedListener(new CurrencyTextWatcher(edtCurrencyValue));
        edtCurrencyQty.addTextChangedListener(new CurrencyTextWatcher(edtCurrencyQty));
    }

    private class CurrencyTextWatcher implements TextWatcher {

        private View view;

        private CurrencyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         * While changing the text in value and quantity it will make it as enable as true and false.
         * When value editText length is more than 0 then it will enable false the qty editText.
         * When qty editText length is more than 0 then it will enable false the value editText.
         *
         * @param s
         */
        @Override
        public void afterTextChanged(Editable s) {
            if (view.getId() == R.id.sell_value_edt_id) {
                if (edtCurrencyValue.getText().toString().length() > 0) {
                    edtCurrencyQty.setEnabled(false);
                    edtCurrencyValue.setEnabled(true);
 /*                   edtCurrencyValue.removeTextChangedListener(this);
                    NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("en", "US"));
                    numberFormat.setMaximumFractionDigits(2);
                    edtCurrencyValue.setText(numberFormat.format(Double.valueOf(s.toString())));
                    edtCurrencyValue.addTextChangedListener(this);
*/
                } else {
                    edtCurrencyQty.setEnabled(true);
                }

            } else if (view.getId() == R.id.sell_qty_edt_id) {
                if (edtCurrencyQty.getText().toString().length() > 0) {
                    edtCurrencyValue.setEnabled(false);
                    edtCurrencyQty.setEnabled(true);
                } else {
                    edtCurrencyValue.setEnabled(true);
                }
            }

        }
    }

}
