package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.BaseActivity;
import com.vivartha.kryptopal.activity.HomeActivity;
import com.vivartha.kryptopal.activity.KryptoActivity;
import com.vivartha.kryptopal.interfaces.ChangeNotify;
import com.vivartha.kryptopal.utility.SessionManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    private View view;
    private Context context;
    private ChangeNotify changeNotifyListener;
    private LinearLayout layoutBalance;
    private LinearLayout layoutPay;
    private LinearLayout layoutAddMoney;
    private LinearLayout layoutKryptoPal;

    private LinearLayout layoutPrepaid;
    private LinearLayout layoutPostpaid;
    private LinearLayout layoutElectricity;
    private LinearLayout layoutDth;
    private LinearLayout layoutMovie;
    private LinearLayout layoutBus;
    private LinearLayout layoutFlight;
    private LinearLayout layoutTrain;

    private LinearLayout layoutProfile;
    private LinearLayout layoutHome;
    private LinearLayout layoutScan;
    private LinearLayout layoutKrypto;
    private LinearLayout layoutShop;
    private double dBalanceAmount;

    private TextView txtCurrencyBalance;
    private SessionManager sessionManager;
    //For showing alert message
    private static final String SNACK_MESSAGE = "This feature is coming soon . . .";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI(view);
        clickListener();
        return view;
    }

    /**
     * For Replacing or Changing from home fragment to another fragment.
     *
     * @param changeNotifyListener
     */
    public void setChangeNotifyListener(ChangeNotify changeNotifyListener) {
        this.changeNotifyListener = changeNotifyListener;
    }

    /**
     * Initialize all the UI Contents
     * That UI contents present on the fragment_home
     *
     * @param view
     */
    private void initUI(View view) {
        context = getActivity();
        sessionManager = new SessionManager(context);
        layoutBalance = (LinearLayout) view.findViewById(R.id.balance_layout_id);
        layoutPay = (LinearLayout) view.findViewById(R.id.pay_layout_id);
        layoutAddMoney = (LinearLayout) view.findViewById(R.id.add_money_layout_id);
        layoutKryptoPal = (LinearLayout) view.findViewById(R.id.krypto_layout_id);

        layoutPrepaid = (LinearLayout) view.findViewById(R.id.layout_mobile_prepaid_id);
        layoutPostpaid = (LinearLayout) view.findViewById(R.id.layout_mobile_postpaid_id);
        layoutElectricity = (LinearLayout) view.findViewById(R.id.layout_electricity_id);
        layoutDth = (LinearLayout) view.findViewById(R.id.layout_dth_id);
        layoutMovie = (LinearLayout) view.findViewById(R.id.layout_movie_id);
        layoutBus = (LinearLayout) view.findViewById(R.id.layout_bus_id);
        layoutFlight = (LinearLayout) view.findViewById(R.id.layout_flight_id);
        layoutTrain = (LinearLayout) view.findViewById(R.id.layout_train_id);

        layoutHome = (LinearLayout) view.findViewById(R.id.home_menu_id);
        layoutShop = (LinearLayout) view.findViewById(R.id.shop_menu_id);
        layoutScan = (LinearLayout) view.findViewById(R.id.scan_menu_id);
        layoutProfile = (LinearLayout) view.findViewById(R.id.home_profile_menu_id);
        layoutKrypto = (LinearLayout) view.findViewById(R.id.krypto_menu_id);

        txtCurrencyBalance = (TextView) view.findViewById(R.id.total_flat_currency_edt_id);
        setConditionCurrencyEditText();
    }

    //Set Single line for currency balance textview
    private void setConditionCurrencyEditText() {
//        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
//        format.setMinimumFractionDigits(2);

        dBalanceAmount = sessionManager.getAddAmount();
        DecimalFormat format = new DecimalFormat("##,##,###.##");
        txtCurrencyBalance.setText(String.valueOf(format.format(dBalanceAmount)));
        txtCurrencyBalance.setSingleLine();
    }

    //Set onClick Listeners for that balance, pay, add money, kryptopal layouts.
    private void clickListener() {
        layoutBalance.setOnClickListener(this);
        layoutPay.setOnClickListener(this);
        layoutAddMoney.setOnClickListener(this);
        layoutKryptoPal.setOnClickListener(this);
        layoutPrepaid.setOnClickListener(this);
        layoutPostpaid.setOnClickListener(this);
        layoutElectricity.setOnClickListener(this);
        layoutDth.setOnClickListener(this);
        layoutMovie.setOnClickListener(this);
        layoutBus.setOnClickListener(this);
        layoutFlight.setOnClickListener(this);
        layoutTrain.setOnClickListener(this);

        layoutProfile.setOnClickListener(this);
        layoutShop.setOnClickListener(this);
        layoutScan.setOnClickListener(this);
        layoutKrypto.setOnClickListener(this);
        layoutHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            /**
             * Change fragment from home fragment to other fragment by using changeNotifyListener
             * If changeNotifyListener is not null then it will replace to other fragment
             */

            case R.id.balance_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(HomeActivity.HOME_FRAGMENT);
                }
                break;

            case R.id.pay_layout_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;
            case R.id.add_money_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(HomeActivity.ADD_MONEY_FRAGMENT);
                }
                break;
            case R.id.krypto_layout_id:
                pageKryptoRedirection();
                break;

            case R.id.layout_mobile_prepaid_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(HomeActivity.PAY_FRAGMENT);
                }
                break;

            case R.id.layout_mobile_postpaid_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_electricity_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_dth_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_movie_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_bus_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_flight_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.layout_train_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.home_menu_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(HomeActivity.HOME_FRAGMENT);
                }
                break;

            case R.id.shop_menu_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.scan_menu_id:
                ((BaseActivity) getActivity()).showAlert(view, SNACK_MESSAGE);
                break;

            case R.id.home_profile_menu_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(HomeActivity.PROFILE_FRAGMENT);
                }
                break;

            case R.id.krypto_menu_id:
                pageKryptoRedirection();
                break;

        }
    }

    private void pageKryptoRedirection() {
        Intent intent = new Intent(getActivity(), KryptoActivity.class);
        startActivity(intent);
    }


}
