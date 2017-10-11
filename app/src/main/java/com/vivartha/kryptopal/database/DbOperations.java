package com.vivartha.kryptopal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vivartha.kryptopal.model.Balance;
import com.vivartha.kryptopal.model.Banks;
import com.vivartha.kryptopal.model.Contact;
import com.vivartha.kryptopal.model.Transaction;
import com.vivartha.kryptopal.model.Values;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANANTH on 27-09-2017.
 */

public class DbOperations extends DbHandler {

    private long status = 0;

    /**
     * @param context
     */
    public DbOperations(Context context) {
        super(context);
    }

    /**
     * Add the currency values into the value table.
     * All the currency values getting from the values fragment
     *
     * @param values
     * @return
     */
    public boolean addValues(Values values) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SYMBOL, values.getCurrencySymbol());
        contentValues.put(USD_PRICE, values.getCurrencyValues());
        contentValues.put(HOUR_PERCENT_RATE, values.getCurrencyPoints());
        status = sqLiteDatabase.insert(VALUE_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return status > 0;
    }

    /**
     * Getting all the added currency symbol from the value table
     *
     * @return
     */
    public List<String> getCurrencyValues() {
        List<String> valuesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(VALUE_TABLE, new String[]{SYMBOL}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                valuesList.add(cursor.getString(0));
            } while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();
        return valuesList;
    }

    /**
     * Getting all the added currency symbol except passing currency symbol from the value table
     *
     * @return
     */
    public List<String> getToCurrencyValues(String symbol) {
        List<String> valuesList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(VALUE_TABLE, new String[]{SYMBOL}, SYMBOL + "!=?", new String[]{symbol}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                valuesList.add(cursor.getString(0));
            } while (cursor.moveToNext());

        }
        cursor.close();
        sqLiteDatabase.close();
        return valuesList;
    }

    /**
     * Add the Banks name into the banks table.
     * All the banks name getting from the add money fragment
     *
     * @param banks
     * @return
     */
    public boolean addBanks(Banks banks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BANKS_NAME, banks.getStrBanks());
        status = sqLiteDatabase.insert(TABLE_BANKS, null, contentValues);
        sqLiteDatabase.close();

        return status > 0;
    }

    /**
     * Get all the banks name added in the bank table
     *
     * @return
     */
    public List<String> getBanks() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_BANKS, new String[]{BANKS_NAME}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    /**
     * Add contact person name into the contact table
     * Contact details getting from the contact fragment
     *
     * @param contact
     * @return
     */
    public boolean addContacts(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, contact.getPersonName());
        status = sqLiteDatabase.insert(TABLE_CONTACTS, null, contentValues);
        sqLiteDatabase.close();

        return status > 0;
    }

    /**
     * Get all the contacts from the contact table
     *
     * @return
     */
    public List<String> getContacts() {

        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_CONTACTS, new String[]{CONTACT_NAME}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    public boolean addTransaction(String type, String transactionFrom, String amount, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRANSACTION_TYPE, type);
        contentValues.put(TRANSACTION_FROM, transactionFrom);
        contentValues.put(TRANSACTION_AMOUNT, amount);
        contentValues.put(TRANSACTION_DATE, date);
        status = sqLiteDatabase.insert(TABLE_TRANSACTION, null, contentValues);
        sqLiteDatabase.close();
        return status > 0;
    }


    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_TRANSACTION, new String[]{TRANSACTION_TYPE, TRANSACTION_FROM,
                TRANSACTION_AMOUNT, TRANSACTION_DATE}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setTransactionTitle(cursor.getString(0));
                transaction.setTransactionName(cursor.getString(1));
                transaction.setTransactionAmount(cursor.getString(2));
                transaction.setTransactionDate(cursor.getString(3));
                list.add(transaction);

            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return list;
    }

}
