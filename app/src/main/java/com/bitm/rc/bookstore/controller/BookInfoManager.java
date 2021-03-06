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
    SQLiteDatabase sqLiteDatabase;
    public BookInfoManager() {
    }

    public BookInfoManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    //method for spinner
    public ArrayList<String> addItems() {
        sqLiteDatabase = databaseHelper.getReadableDatabase();
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

/*******************add book info*********************/
    public long addBookInfo(BookInfo bookInfo){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.BOOK_ID, bookInfo.getId());
        contentValues.put(databaseHelper.BOOK_NAME, bookInfo.getBookName());
        contentValues.put(databaseHelper.AUTHOR_NAME,bookInfo.getAuthor());
        contentValues.put(databaseHelper.EDITION,bookInfo.getEdition());
        contentValues.put(databaseHelper.PUBLISHER, bookInfo.getPublisherInfoList());
        contentValues.put(databaseHelper.QTY, bookInfo.getQuantity());
        contentValues.put(databaseHelper.UNIT_PRICE, bookInfo.getPrice());
        contentValues.put(databaseHelper.COUNTRY, "");
        contentValues.put(databaseHelper.LAN, bookInfo.getLanguage());
        long insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_BOOK_INFO,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }
/***********************end*************************/

    /*******************edit book info*********************/
    public long editBookInfo(BookInfo bookInfo){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.BOOK_ID, bookInfo.getId());
        contentValues.put(databaseHelper.BOOK_NAME, bookInfo.getBookName());
        contentValues.put(databaseHelper.AUTHOR_NAME,bookInfo.getAuthor());
        contentValues.put(databaseHelper.EDITION,bookInfo.getEdition());
        contentValues.put(databaseHelper.PUBLISHER, String.valueOf(bookInfo.getPublisherInfoList()));
        contentValues.put(databaseHelper.QTY, bookInfo.getQuantity());
        contentValues.put(databaseHelper.UNIT_PRICE, bookInfo.getPrice());
        contentValues.put(databaseHelper.COUNTRY, bookInfo.getCountry());
        contentValues.put(databaseHelper.LAN, bookInfo.getLanguage());
//        long insertRow1 = sqLiteDatabase.update(databaseHelper.TABLE_NAME_BOOK_INFO,null,DatabaseHelper.BOOK_ID+" =?",new String[]{String.valueOf(bookInfo.getId())},contentValues);


        long insertRow =   sqLiteDatabase.update(DatabaseHelper.TABLE_NAME_BOOK_INFO, contentValues, DatabaseHelper.BOOK_ID + " = ?",
                new String[]{String.valueOf(bookInfo.getId())});
        sqLiteDatabase.close();
        return insertRow;

    }
/***********************end*************************/

/* delete/Remove record from recycler view and database*/
public boolean delete(int id)
{
    try {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        int result=sqLiteDatabase.delete(databaseHelper.TABLE_NAME_BOOK_INFO,DatabaseHelper.BOOK_ID+" =?",new String[]{String.valueOf(id)});
        if(result>0) {
            return true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    /************get recycler view list data method**************/
    public ArrayList<BookInfo> getBookList(){
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        ArrayList<BookInfo>bookInfos=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.BOOK_ID));
//                int id=cursor.getInt(cursor.getColumnIndex("1"));
                String bookName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.BOOK_NAME));
                String authorName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.AUTHOR_NAME));
                String edition=cursor.getString(cursor.getColumnIndex(DatabaseHelper.EDITION));
                int quantity=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.QTY));
                String price=cursor.getString(cursor.getColumnIndex(DatabaseHelper.UNIT_PRICE));
                String publisher=cursor.getString(cursor.getColumnIndex(DatabaseHelper.PUBLISHER));
                String lan=cursor.getString(cursor.getColumnIndex(DatabaseHelper.LAN));
                BookInfo bookInfo=new BookInfo(id,bookName,authorName,edition,publisher,quantity,price,lan);
               /* if(!bookInfo.equals(null)){

                }else {
                    String [] ss = {"No Record Found"};
                    bookInfos.add(ss);
                }*/
                bookInfos.add(bookInfo);
            }while(cursor.moveToNext());
        }
        return bookInfos;
    }
/**********************end***************************/

public int getTotalStock(){
    sqLiteDatabase=databaseHelper.getReadableDatabase();
//    int totalStock=0;
    String selectQuery="select SUM("+DatabaseHelper.QTY+") from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
//    String selectQuery="select SUM(quantity) from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
    cursor.moveToFirst();
    int totalStock= cursor.getInt(0);
    return totalStock;
}
public String getTotalItem(){
    sqLiteDatabase=databaseHelper.getReadableDatabase();
    String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_BOOK_INFO;
    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
    int totalItem = cursor.getCount();
    cursor.close();
    return "Total Item " +totalItem;
}

   /* public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }*/
}
