package com.vivartha.kryptopal.model;

/**
 * Created by ANANTH on 21-09-2017.
 */

public class Prepaid {

    private String id;
    private int selectedPosition;
    private Integer currencyTypeImage;
    private double currencyPoints;
    private int currencyValues;
    private Integer currencyStatus;
    private String currencyName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public Integer getCurrencyTypeImage() {
        return currencyTypeImage;
    }

    public void setCurrencyTypeImage(Integer currencyTypeImage) {
        this.currencyTypeImage = currencyTypeImage;
    }

    public double getCurrencyPoints() {
        return currencyPoints;
    }

    public void setCurrencyPoints(double currencyPoints) {
        this.currencyPoints = currencyPoints;
    }

    public int getCurrencyValues() {
        return currencyValues;
    }

    public void setCurrencyValues(int currencyValues) {
        this.currencyValues = currencyValues;
    }

    public Integer getCurrencyStatus() {
        return currencyStatus;
    }

    public void setCurrencyStatus(Integer currencyStatus) {
        this.currencyStatus = currencyStatus;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
