package com.bitm.rc.bookstore.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.bitm.rc.bookstore.model.PublisherInfo;
import java.util.ArrayList;

public class BookInfoManager {
    DatabaseHelper databaseHelper;

    public BookInfoManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    public ArrayList<String>  addItems() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ArrayList<String> list = new ArrayList();
        list.add("Select One");
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM publisher_info WHERE 1 = 1", null);
//        Cursor c = db.rawQuery("SELECT * FROM publisher_info WHERE TRIM(name) = '"+name.trim()+"'", null);
        if (c.moveToFirst()){
            do {
                String publisherName= c.getString(c.getColumnIndex(databaseHelper.PUBLISHER_NAME));
                PublisherInfo publisherInfo=new PublisherInfo(publisherName);
                list.add(publisherInfo.getName());
            } while(c.moveToNext());
        }
        c.close();
        sqLiteDatabase.close();
        return list;
    }


}
