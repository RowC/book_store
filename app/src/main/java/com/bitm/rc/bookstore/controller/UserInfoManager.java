package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.UserInfo;

public class UserInfoManager {
    DatabaseHelper databaseHelper;

    public UserInfoManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    public long addUser(UserInfo userInfo){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.COLUMN_FULL_NAME,userInfo.getUserFullName());
        contentValues.put(databaseHelper.COLUMN_USER_NAME,userInfo.getUserName());
        contentValues.put(databaseHelper.COLUMN_PASSWORD,userInfo.getPassword());
        long insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }
}
