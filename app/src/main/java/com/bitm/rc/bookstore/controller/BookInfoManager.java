package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.ArrayList;

public class BookInfoManager {
    DatabaseHelper databaseHelper;

    public BookInfoManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //method for spinner
    public ArrayList<String> addItems() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ArrayList<String> list = new ArrayList();
        list.add("Select One");
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM publisher_info WHERE 1 = 1", null);
//        Cursor c = db.rawQuery("SELECT * FROM publisher_info WHERE TRIM(name) = '"+name.trim()+"'", null);
        if (c.moveToFirst()) {
            do {
                String publisherName = c.getString(c.getColumnIndex(databaseHelper.PUBLISHER_NAME));
                PublisherInfo publisherInfo = new PublisherInfo(publisherName);
                list.add(publisherInfo.getName());
            } while (c.moveToNext());
        }
        c.close();
        sqLiteDatabase.close();
        return list;
    }
    /************end******************/


    public long addBookInfo(BookInfo bookInfo){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.BOOK_NAME, bookInfo.getBookName());
        contentValues.put(databaseHelper.AUTHOR_NAME,bookInfo.getAuthor());
        contentValues.put(databaseHelper.EDITION,bookInfo.getEdition());
        contentValues.put(databaseHelper.PUBLISHER, String.valueOf(bookInfo.getPublisherInfoList()));
        contentValues.put(databaseHelper.QTY, bookInfo.getQuantity());
        contentValues.put(databaseHelper.UNIT_PRICE, bookInfo.getPrice());
        contentValues.put(databaseHelper.COUNTRY, bookInfo.getCountry());
        contentValues.put(databaseHelper.LAN, bookInfo.getLanguage());
        long insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_BOOK_INFO,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }

    public ArrayList<BookInfo> getBookList(){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        ArrayList<BookInfo>bookInfos=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PUBLISHER_ID));
                String bookName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.BOOK_NAME));
                String authorName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.AUTHOR_NAME));
                String edition=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EDITION));
                String price=cursor.getString(cursor.getColumnIndex(DatabaseHelper.UNIT_PRICE));
                int quantity=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.QTY));
                BookInfo bookInfo=new BookInfo(id,bookName,authorName,edition,price,quantity);
                bookInfos.add(bookInfo);
            }while(cursor.moveToNext());
        }
        return bookInfos;
    }
}
