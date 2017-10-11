package com.vivartha.kryptopal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANANTH on 25-09-2017.
 */

public class DbHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "kryptoPalManager";

    public static final String TABLE_CURRENCY_BALANCE = "currency_balance_table";
    public static final String BALANCE_ID = "balance_id";
    public static final String AVAIL_BALANCE = "available_balance";

    public static final String TABLE_ADD_AMOUNT = "add_amount_table";
    public static final String ADD_AMOUNT_ID = "add_amount_id";
    public static final String ADD_DATE = "add_date";
    public static final String ADD_AMOUNT = "add_amount";

    public static final String TABLE_PREPAID = "prepaid_table";
    public static final String PREPAID_AMOUNT_ID = "prepaid_amount_id";
    public static final String CURRENCY_TYPE = "currency_type";
    public static final String PREPAID_AMOUNT = "prepaid_amount";
    public static final String PREPAID_DATE = "prepaid_date";

    public static final String VALUE_TABLE = "value_table";
    public static final String VALUE_ID = "value_id";
    public static final String SYMBOL = "currency_symbol";
    public static final String USD_PRICE = "usd_price";
    public static final String HOUR_PERCENT_RATE = "hour_percent_rate";

    public static final String TABLE_BANKS = "bank_table";
    public static final String BANKS_ID = "bank_id";
    public static final String BANKS_NAME = "bank_name";

    public static final String TABLE_CONTACTS = "contact_table";
    public static final String CONTACTS_ID = "contact_id";
    public static final String CONTACT_NAME = "contact_name";

    public static final String TABLE_TRANSACTION = "transaction_table";
    public static final String TRANSACTION_ID = "transaction_id";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String TRANSACTION_FROM = "transaction_from";
    public static final String TRANSACTION_AMOUNT = "transaction_amount";
    public static final String TRANSACTION_DATE = "transaction_date";
    public static final String AVAILABLE_BALANCE = "available_balance";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String CREATE_VALUE_TABLE = "CREATE TABLE " + VALUE_TABLE + "(" + VALUE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SYMBOL + " TEXT, " + HOUR_PERCENT_RATE + " REAL, " + USD_PRICE + " REAL);";

    private String CREATE_BANK_TABLE = "CREATE TABLE " + TABLE_BANKS + "(" + BANKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BANKS_NAME + " TEXT);";

    private String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_NAME + " TEXT);";

    private String CREATE_ADD_AMOUNT_TABLE = "CREATE TABLE " + TABLE_ADD_AMOUNT + "(" + ADD_AMOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BANKS_NAME + " TEXT," + ADD_AMOUNT + " TEXT," + ADD_DATE + " TEXT);";

    private String CREATE_PREPAID_AMOUNT_TABLE = "CREATE TABLE " + TABLE_PREPAID + "(" + PREPAID_AMOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CURRENCY_TYPE + " TEXT," + PREPAID_AMOUNT + " TEXT," + PREPAID_DATE + " TEXT);";

    private String CREATE_BALANCE_TABLE = "CREATE TABLE " + TABLE_CURRENCY_BALANCE + "(" + BALANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AVAIL_BALANCE + " TEXT);";

    private String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "(" + TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRANSACTION_TYPE + " TEXT," + TRANSACTION_FROM + " TEXT,"+ TRANSACTION_AMOUNT + " TEXT,"
            + TRANSACTION_DATE + " TEXT,"+ AVAILABLE_BALANCE + " TEXT);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VALUE_TABLE);
        db.execSQL(CREATE_BANK_TABLE);
        db.execSQL(CREATE_CONTACT_TABLE);
        db.execSQL(CREATE_ADD_AMOUNT_TABLE);
        db.execSQL(CREATE_PREPAID_AMOUNT_TABLE);
        db.execSQL(CREATE_BALANCE_TABLE);
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + CREATE_VALUE_TABLE);
        db.execSQL("drop table if exists" + CREATE_BANK_TABLE);
        db.execSQL("drop table if exists" + CREATE_CONTACT_TABLE);
        db.execSQL("drop table if exists" + CREATE_ADD_AMOUNT_TABLE);
        db.execSQL("drop table if exists" + CREATE_PREPAID_AMOUNT_TABLE);
        db.execSQL("drop table if exists" + CREATE_BALANCE_TABLE);
        db.execSQL("drop table if exists" + CREATE_TRANSACTION_TABLE);
        onCreate(db);
    }
}
