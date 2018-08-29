package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.ArrayList;

public class PublisherInfoManager {

    public DatabaseHelper databaseHelper;
    public  SQLiteDatabase sqLiteDatabase;

    public PublisherInfoManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    public long addPublisher(PublisherInfo publisherInfo){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(DatabaseHelper.PUBLISHER_NAME,publisherInfo.getName());
        contentValues.put(DatabaseHelper.PUBLISHER_ADDRESS,publisherInfo.getAddress());
        contentValues.put(DatabaseHelper.ENTRY_DATE,publisherInfo.getDate().toString());
        long insertRow = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME_PUBLISHER_INFO,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }

    /************get recycler view list data method**************/
    public ArrayList<PublisherInfo> getBookList(){
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        ArrayList<PublisherInfo>publisherInfos=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                 String publiserName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.PUBLISHER_NAME));
                String publiserAddress=cursor.getString(cursor.getColumnIndex(DatabaseHelper.PUBLISHER_ADDRESS));

                PublisherInfo publisherInfo=new PublisherInfo(publiserName,publiserAddress);
               /* if(!bookInfo.equals(null)){

                }else {
                    String [] ss = {"No Record Found"};
                    bookInfos.add(ss);
                }*/
                publisherInfos.add(publisherInfo);
            }while(cursor.moveToNext());
        }
        return publisherInfos;
    }
/**********************end***************************/
}
