package com.bitm.rc.bookstore.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "book_store";
    public static final String TABLE_NAME = "user_info";
    public static final Integer VERSION = 1;
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_PASSWORD = "password";

    public static final String TABLE_NAME_PUBLISHER_INFO = "publisher_info";
    public static final String PUBLISHER_ID = "publisher_id";
    public static final String PUBLISHER_NAME = "publisher_name";
    public static final String PUBLISHER_ADDRESS = "publisher_addrs";
    public static final String ENTRY_DATE = "entry_date";

    public static final String TABLE_NAME_BOOK_INFO = "book_info";
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String AUTHOR_NAME = "author_name";
    public static final String EDITION = "edition";
    public static final String PUBLISHER = "publisher";
    public static final String QTY = "quantity";
    public static final String UNIT_PRICE = "unit_price";
    public static final String COUNTRY = "country";
    public static final String LAN = "lan";

    public static final String CREATE_USER_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_USER_ID + " integer primary key autoincrement," +
            "" + COLUMN_FULL_NAME + " text," + COLUMN_USER_NAME + " text," + COLUMN_PASSWORD + " text,CONSTRAINT unique_user_name UNIQUE (" + COLUMN_USER_NAME + "));";

    public static final String CREATE_PUBLISHER_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PUBLISHER_INFO + " (" + PUBLISHER_ID + " integer primary key autoincrement," +
            "" + PUBLISHER_NAME + " text," + PUBLISHER_ADDRESS + " text," + ENTRY_DATE + " text,CONSTRAINT unique_user_name UNIQUE (PUBLISHER_NAME));";

    public static final String CREATE_BOOK_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_BOOK_INFO + " (" + BOOK_ID + " integer primary key autoincrement," +
            "" + BOOK_NAME + " text," + AUTHOR_NAME + " text," + EDITION + " text," + PUBLISHER + " text," + QTY + " text," + UNIT_PRICE + " text," + COUNTRY + " text," + LAN + " text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*******drop table*****/
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + CREATE_USER_QUERY + "'");
        /******end*******/
        // creating required tables
        sqLiteDatabase.execSQL(CREATE_USER_QUERY);
        sqLiteDatabase.execSQL(CREATE_PUBLISHER_QUERY);
        sqLiteDatabase.execSQL(CREATE_BOOK_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PUBLISHER_INFO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOK_INFO);
        // create new tables
        onCreate(sqLiteDatabase);
    }
}
