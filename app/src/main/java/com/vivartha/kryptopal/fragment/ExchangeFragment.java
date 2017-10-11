package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.BaseActivity;
import com.vivartha.kryptopal.database.DbOperations;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFragment extends Fragment {

    private View view;
    private Spinner spnrFrom;
    private Spinner spnrTo;
    private EditText edtCurrencyValue;
    private EditText edtCurrencyQty;
    private Button btnExchange;
    private Context context;
    private List<String> valuesSymbolList;
    private ArrayAdapter<String> arrayAdapter;
    private DbOperations dbOperations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_exchange, container, false);
        initUI();
        getFromValue();

        clickListeners();
        return view;
    }

    private void initUI() {
        context = getActivity();
        dbOperations = new DbOperations(context);
        spnrFrom = (Spinner) view.findViewById(R.id.exchange_from_spnr_id);
        spnrTo = (Spinner) view.findViewById(R.id.exchange_to_spnr_id);
        edtCurrencyValue = (EditText) view.findViewById(R.id.exchange_value_edt_id);
        edtCurrencyQty = (EditText) view.findViewById(R.id.exchange_qty_edt_id);
        btnExchange = (Button) view.findViewById(R.id.exchange_btn_id);
    }



    private void getFromValue() {
        valuesSymbolList = dbOperations.getCurrencyValues();
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, valuesSymbolList);
        spnrFrom.setAdapter(arrayAdapter);
        spnrFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getToValue(spnrFrom.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getToValue(String symbol) {
        valuesSymbolList = dbOperations.getToCurrencyValues(symbol);
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, valuesSymbolList);
        spnrTo.setAdapter(arrayAdapter);
    }
    private void clickListeners() {
        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) context).showSuccessAlertDialogue(context, "Exchanged successful");
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
            if (view.getId() == R.id.exchange_value_edt_id) {
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

            } else if (view.getId() == R.id.exchange_qty_edt_id) {
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
