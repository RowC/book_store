package com.bitm.rc.bookstore;

import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.db.BookInfoManager;
import com.bitm.rc.bookstore.model.PublisherInfo;

public class BookInfoActivity extends AppCompatActivity {

    Spinner publisher;
    BookInfoManager bookInfoManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        addItemsOnSpinner();
    }


    public void addItemsOnSpinner() {
        publisher = findViewById(R.id.publisher);
        bookInfoManager= new BookInfoManager(this);
        ArrayAdapter<PublisherInfo> dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, bookInfoManager.addItems());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            publisher.setPopupBackgroundResource(R.color.holo_green);
        }*/
        publisher.setAdapter(dataAdapter);
    }
}
