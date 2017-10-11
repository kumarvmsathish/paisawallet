package com.vivartha.kryptopal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vivartha.kryptopal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RadioButton rBtnKpxCards;
    private RadioButton rBtnPpxCards;
    private RadioGroup rGroupCards;
    private ImageView imgKPXCard;
    private ImageView imgPPXCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cards, container, false);
        initUI(view);
        clickListeners();

        return view;
    }

    // Initialize the UI Components belongs to the fragment_cards
    private void initUI(View view) {
        rGroupCards = (RadioGroup) view.findViewById(R.id.card_rGroup_id);
        rBtnKpxCards = (RadioButton) view.findViewById(R.id.kpx_card_id);
        rBtnPpxCards = (RadioButton) view.findViewById(R.id.ppx_card_id);
        imgKPXCard = (ImageView) view.findViewById(R.id.kpx_card_image_id);
        imgPPXCard = (ImageView) view.findViewById(R.id.ppx_card_image_id);
    }

    //ClickListeners for radioButton and ImageView

    private void clickListeners() {
        imgKPXCard.setOnClickListener(this);
        imgPPXCard.setOnClickListener(this);
        rBtnKpxCards.setOnClickListener(this);
        rBtnPpxCards.setOnClickListener(this);
    }

    /**
     * If Pressed the Kpx radioButton or Kpx Image it will unCheck the Ppx radioButton or Ppx Image
     * If Pressed the Ppx radioButton or Ppx Image it will unCheck the Kpx radioButton or Kpx Image
     * @param v
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.kpx_card_id:
                rBtnKpxCards.setChecked(true);
                rBtnPpxCards.setChecked(false);
                break;

            case R.id.ppx_card_id:
                rBtnKpxCards.setChecked(false);
                rBtnPpxCards.setChecked(true);
                break;

            case R.id.kpx_card_image_id:
                rBtnKpxCards.setChecked(true);
                rBtnPpxCards.setChecked(false);
                break;

            case R.id.ppx_card_image_id:
                rBtnKpxCards.setChecked(false);
                rBtnPpxCards.setChecked(true);
                break;
        }

    }
}
