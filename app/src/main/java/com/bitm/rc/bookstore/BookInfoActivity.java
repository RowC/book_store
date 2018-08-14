package com.bitm.rc.bookstore;

import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.db.BookInfoManager;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.Date;

public class BookInfoActivity extends AppCompatActivity {
    EditText bookName;
    EditText author;
    EditText edition;
    EditText quantity;
    EditText price;
    EditText country;
    EditText language;
    Spinner publisher;
    BookInfoManager bookInfoManager =new BookInfoManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        bookName = findViewById(R.id.book_name);
        author = findViewById(R.id.author_name);
        edition = findViewById(R.id.edition);
        quantity = findViewById(R.id.qty);
        price = findViewById(R.id.price);
        country = findViewById(R.id.country);
        language = findViewById(R.id.language);
        addItemsOnSpinner();
    }
    public void add(View view) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(bookName.getText().toString());
        bookInfo.setAuthor(author.getText().toString());
        bookInfo.setEdition(edition.getText().toString());
        bookInfo.setQuantity(Integer.getInteger(quantity.getText().toString()));
        bookInfo.setPrice(price.getText().toString());
        bookInfo.setCountry(country.getText().toString());
        bookInfo.setLanguage(language.getText().toString());
        long insertedRow = bookInfoManager.addBookInfo(bookInfo);
        if (insertedRow > 0) {
            Toast.makeText(this, "" + insertedRow, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }
    /*******Spinner*********/
    public void addItemsOnSpinner() {
        publisher = findViewById(R.id.publisher);
//        bookInfoManager = new BookInfoManager(this);
        ArrayAdapter<PublisherInfo> dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, bookInfoManager.addItems());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            publisher.setPopupBackgroundResource(R.color.holo_green);
        }*/
        publisher.setAdapter(dataAdapter);
    }

    /**********end**************/


}
