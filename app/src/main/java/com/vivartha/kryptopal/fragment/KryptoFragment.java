package com.vivartha.kryptopal.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vivartha.kryptopal.R;
import com.vivartha.kryptopal.activity.KryptoActivity;
import com.vivartha.kryptopal.interfaces.ChangeNotify;
import com.vivartha.kryptopal.utility.SessionManager;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class KryptoFragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout layoutIdentity;
    private LinearLayout layoutValues;
    private LinearLayout layoutContacts;
    private LinearLayout layoutRewards;
    private LinearLayout layoutCards;
    private LinearLayout layoutTransaction;

    private LinearLayout layoutExchange;
    private LinearLayout layoutSend;
    private LinearLayout layoutRequest;
    private LinearLayout layoutTransfer;
    private LinearLayout layoutSell;
    private LinearLayout layoutBuy;

    private ChangeNotify changeNotifyListener;
    private Context mContext;
    private TextView txtBalance;
    private SessionManager sessionManager;
    private ImageView imgArrow;
    private TextView txtValueDetails;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_krypto, container, false);
        initUI(view);
        kryptoBalance();
        clickListener();
        return view;
    }

    /**
     * For Replacing or Changing from krypto fragment to another fragment.
     *
     * @param changeNotifyListener
     */
    public void setChangeNotifyListener(ChangeNotify changeNotifyListener) {
        this.changeNotifyListener = changeNotifyListener;
    }

    /**
     * Initialize all the UI Contents
     * That UI contents present on the fragment_krypto
     *
     * @param view
     */
    private void initUI(View view) {
        mContext = getActivity();
        txtValueDetails = (TextView) view.findViewById(R.id.details_txt_id);
        imgArrow = (ImageView) view.findViewById(R.id.krypto_balance_arrow_id);
        txtBalance = (TextView) view.findViewById(R.id.balance_txt_id);
        layoutIdentity = (LinearLayout) view.findViewById(R.id.identity_layout_id);
        layoutValues = (LinearLayout) view.findViewById(R.id.values_layout_id);
        layoutContacts = (LinearLayout) view.findViewById(R.id.contact_layout_id);
        layoutCards = (LinearLayout) view.findViewById(R.id.cards_layout_id);
        layoutRewards = (LinearLayout) view.findViewById(R.id.rewards_layout_id);
        layoutTransaction = (LinearLayout) view.findViewById(R.id.transaction_layout_id);

        layoutExchange = (LinearLayout) view.findViewById(R.id.exchange_layout_id);
        layoutSend = (LinearLayout) view.findViewById(R.id.send_layout_id);
        layoutRequest = (LinearLayout) view.findViewById(R.id.request_layout_id);
        layoutTransfer = (LinearLayout) view.findViewById(R.id.transfer_layout_id);
        layoutSell = (LinearLayout) view.findViewById(R.id.sell_layout_id);
        layoutBuy = (LinearLayout) view.findViewById(R.id.buy_layout_id);

    }


    private void kryptoBalance() {
        sessionManager = new SessionManager(mContext);
        if (sessionManager.getKryptoBalance() == 0) {
            txtBalance.setText(getResources().getString(R.string.default_amount));
            imgArrow.setImageResource(R.drawable.down_arrow_icon);
        } else {
            if (sessionManager.getHourPercent() > 0) {
                imgArrow.setImageResource(R.drawable.up_arrow_icon);
            } else {
                imgArrow.setImageResource(R.drawable.down_arrow_icon);
            }
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
            numberFormat.setMaximumFractionDigits(2);
            txtBalance.setText(String.valueOf(numberFormat.format(sessionManager.getKryptoBalance())));

        }

    }

    /**
     * Set onClick Listeners for KryptoPal Features Identity, Values, Contacts, Cards, Rewards and Transactions layouts.
     * Set onClick Listeners for Action KryptoPal Exchange, Send, Request, Transfer, Sell, Buy layouts
     */

    private void clickListener() {

        // listener for KryptoPal Features
        layoutIdentity.setOnClickListener(this);
        layoutValues.setOnClickListener(this);
        layoutContacts.setOnClickListener(this);
        layoutCards.setOnClickListener(this);
        layoutRewards.setOnClickListener(this);
        layoutTransaction.setOnClickListener(this);

        // Listeners for Action KryptoPal
        layoutExchange.setOnClickListener(this);
        layoutSend.setOnClickListener(this);
        layoutRequest.setOnClickListener(this);
        layoutTransfer.setOnClickListener(this);
        layoutSell.setOnClickListener(this);
        layoutBuy.setOnClickListener(this);

        txtValueDetails.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.identity_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.IDENTITY_FRAGMENT);
                }
                break;

            case R.id.values_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.VALUE_FRAGMENT);
                }
                break;

            case R.id.contact_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.CONTACT_FRAGMENT);
                }
                break;

            case R.id.cards_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.CARDS_FRAGMENT);
                }
                break;

            case R.id.rewards_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.REWARDS_FRAGMENT);
                }
                break;

            case R.id.transaction_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.TRANSACTION_FRAGMENT);
                }
                break;
            case R.id.exchange_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.EXCHANGE_FRAGMENT);
                }
                break;

            case R.id.send_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.SEND_FRAGMENT);
                }
                break;

            case R.id.request_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.REQUEST_FRAGMENT);
                }
                break;

            case R.id.transfer_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.TRANSFER_FRAGMENT);
                }
                break;

            case R.id.sell_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.SELL_FRAGMENT);
                }
                break;

            case R.id.buy_layout_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.BUY_FRAGMENT);
                }
                break;

            case R.id.details_txt_id:
                if (changeNotifyListener != null) {
                    changeNotifyListener.changeFragment(KryptoActivity.VALUE_FRAGMENT);
                }
                break;

        }

    }

}
