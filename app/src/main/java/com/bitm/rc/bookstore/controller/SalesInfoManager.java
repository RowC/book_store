package com.bitm.rc.bookstore.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.BookSales;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.ArrayList;

public class SalesInfoManager {
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    public SalesInfoManager() {
    }

    public SalesInfoManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }


/*******************add book info*********************/
    public long addSalesInfo(BookSales bookSales){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.SALES_ID, bookSales.getId());
        contentValues.put(databaseHelper.SALES_QTY, bookSales.getSalesQty());
        contentValues.put(databaseHelper.SALES_PRICE, bookSales.getSalesPrice());
        contentValues.put(databaseHelper.SALES_DATE, bookSales.getSalesDate());
        contentValues.put(databaseHelper.BOOK_INFO_ID, bookSales.getBookInfoId());
        long insertRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME_SALES_INFO,null,contentValues);
        sqLiteDatabase.close();
        return insertRow;
    }
/***********************end*************************/

    /*******************edit book info*********************/
    public long editSalesInfo(BookSales bookSales){
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(databaseHelper.SALES_ID, bookSales.getId());
        contentValues.put(databaseHelper.SALES_QTY, bookSales.getSalesQty());
        contentValues.put(databaseHelper.SALES_PRICE, bookSales.getSalesPrice());
        contentValues.put(databaseHelper.SALES_DATE, bookSales.getSalesDate());
        contentValues.put(databaseHelper.BOOK_INFO_ID, bookSales.getBookInfoId());
       long insertRow =   sqLiteDatabase.update(DatabaseHelper.TABLE_NAME_SALES_INFO, contentValues, DatabaseHelper.SALES_ID + " = ?",
                new String[]{String.valueOf(bookSales.getId())});
        sqLiteDatabase.close();
        return insertRow;

    }
/***********************end*************************/

/* delete/Remove record from recycler view and database*/
public boolean delete(int id)
{
    try {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        int result=sqLiteDatabase.delete(databaseHelper.TABLE_NAME_SALES_INFO,DatabaseHelper.SALES_ID+" =?",new String[]{String.valueOf(id)});
        if(result>0) {
            return true;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    /************get recycler view list data method**************/
    public ArrayList<BookSales> getSalesInfoList(){
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        ArrayList<BookSales>bookSalesArrayList=new ArrayList<>();
        String selectQuery="select * from "+DatabaseHelper.TABLE_NAME_SALES_INFO;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SALES_ID));
                int salesQuantity=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.SALES_QTY));
                String salesPrice=cursor.getString(cursor.getColumnIndex(DatabaseHelper.SALES_PRICE));
                String salesDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.SALES_DATE));
                int bookInfoId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.BOOK_INFO_ID));
                BookSales bookSales=new BookSales(id,salesQuantity,salesPrice,salesDate,bookInfoId);
               /* if(!bookInfo.equals(null)){

                }else {
                    String [] ss = {"No Record Found"};
                    bookInfos.add(ss);
                }*/
                bookSalesArrayList.add(bookSales);
            }while(cursor.moveToNext());
        }
        return bookSalesArrayList;
    }
/**********************end***************************/

public String getTotalSales(){
    sqLiteDatabase=databaseHelper.getReadableDatabase();
    String selectQuery="select SUM("+DatabaseHelper.SALES_QTY+") from "+DatabaseHelper.TABLE_NAME_SALES_INFO;
    Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
    cursor.moveToFirst();
    int totalSales= cursor.getInt(0);
    return "Total Sales "+totalSales;
}
}
