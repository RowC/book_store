package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.UserInfo;

import java.util.ArrayList;

public class UserInfoManager {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public UserInfoManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    public long addUser(UserInfo userInfo){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        SQLiteDatabase sqLiteDatabase2 = databaseHelper.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.COLUMN_USER_ID,userInfo.getId());
        contentValues.put(databaseHelper.COLUMN_FULL_NAME,userInfo.getUserFullName());
        contentValues.put(databaseHelper.COLUMN_USER_NAME,userInfo.getUserName());
        contentValues.put(databaseHelper.COLUMN_PASSWORD,userInfo.getPassword());
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_USER_INFO;
        Cursor cursor = sqLiteDatabase2.rawQuery(selectQuery, null);
        long totalRow = cursor.getCount();
//        long insertRow = 0;
        if(totalRow==0){
            totalRow=0;
        }else {
            totalRow+=totalRow;
        }

        if(totalRow<2){
            totalRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_USER_INFO,null,contentValues);// return long value
            cursor.close();
        }
        sqLiteDatabase.close();
        return totalRow;

    }
    /*******************edit book info*********************/
    public long editUserInfo(UserInfo userInfo){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.COLUMN_FULL_NAME, userInfo.getUserFullName());
        contentValues.put(databaseHelper.COLUMN_USER_NAME, userInfo.getUserName());
        contentValues.put(databaseHelper.COLUMN_PASSWORD, userInfo.getPassword());
        long insertRow =   sqLiteDatabase.update(DatabaseHelper.TABLE_NAME_USER_INFO, contentValues, DatabaseHelper.COLUMN_USER_NAME + " = ?",
                new String[]{String.valueOf(userInfo.getUserName())});
        sqLiteDatabase.close();
        return insertRow;

    }
/***********************end*************************/



        /************get recycler view list data method**************/
        public ArrayList<UserInfo> getUserInfoList(){
            sqLiteDatabase=databaseHelper.getReadableDatabase();
            ArrayList<UserInfo> userInfoArrayList=new ArrayList<>();
            String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_USER_INFO;
            Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do{
//                    int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SALES_ID));
                    String userFullName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FULL_NAME));
                    String userName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME));
                    String userPass=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));
                    UserInfo userInfo=new UserInfo(userFullName,userName,userPass);
                    userInfoArrayList.add(userInfo);
                }while(cursor.moveToNext());
            }
            return userInfoArrayList;
        }
/**********************end***************************/


/* delete/Remove record from recycler view and database*/
public boolean delete(String id)
{
    try {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
//        int result=sqLiteDatabase.delete(databaseHelper.TABLE_NAME_USER_INFO,DatabaseHelper.COLUMN_USER_ID+" =?",new String[]{String.valueOf(id)});
        int result=sqLiteDatabase.delete(databaseHelper.TABLE_NAME_USER_INFO,DatabaseHelper.COLUMN_USER_NAME+" =?",new String[]{String.valueOf(id)});
        if(result>0) {
            return true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
