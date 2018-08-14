package com.bitm.rc.bookstore.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.model.PublisherInfo;

public class PublisherInfoManager {

    DatabaseHelper databaseHelper;

    public PublisherInfoManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    public long addPublisher(PublisherInfo publisherInfo){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.PUBLISHER_NAME,publisherInfo.getName());
        contentValues.put(databaseHelper.PUBLISHER_ADDRESS,publisherInfo.getAddress());
        contentValues.put(databaseHelper.ENTRY_DATE,publisherInfo.getDate().toString());
        long insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_PUBLISHER_INFO,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }
}
