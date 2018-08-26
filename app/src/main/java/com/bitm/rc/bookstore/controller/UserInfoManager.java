package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        SQLiteDatabase sqLiteDatabase2 = databaseHelper.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.COLUMN_FULL_NAME,userInfo.getUserFullName());
        contentValues.put(databaseHelper.COLUMN_USER_NAME,userInfo.getUserName());
        contentValues.put(databaseHelper.COLUMN_PASSWORD,userInfo.getPassword());
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_USER_INFO;
        Cursor cursor = sqLiteDatabase2.rawQuery(selectQuery, null);
        long totalRow = cursor.getCount();
        long insertRow=0;
        if(totalRow<1){
            insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_USER_INFO,null,contentValues);
            cursor.close();
        }
        sqLiteDatabase.close();
        return insertRow+totalRow;

    }
}
