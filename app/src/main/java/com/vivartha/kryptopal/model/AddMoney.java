package com.vivartha.kryptopal.model;

/**
 * Created by ANANTH on 04-10-2017.
 */

public class AddMoney {
    private int strAddMoneyId;
    private String strBanks;
    private String strAmount;
    private String strDate;

    public int getStrAddMoneyId() {
        return strAddMoneyId;
    }

    public void setStrAddMoneyId(int strAddMoneyId) {
        this.strAddMoneyId = strAddMoneyId;
    }

    public String getStrBanks() {
        return strBanks;
    }

    public void setStrBanks(String strBanks) {
        this.strBanks = strBanks;
    }

    public String getStrAmount() {
        return strAmount;
    }

    public void setStrAmount(String strAmount) {
        this.strAmount = strAmount;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
