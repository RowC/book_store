package com.bitm.rc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.BookInfoManager;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.PublisherInfo;

public class BookInfoActivity extends AppCompatActivity {
    EditText bookName;
    EditText author;
    EditText edition;
    EditText quantity;
    EditText price;
    EditText language;
    Spinner publisher;
    Button edit;
    Button cancel;
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
//        country = findViewById(R.id.country);
        language = findViewById(R.id.language);
        publisher = findViewById(R.id.publisher);
        addItemsOnSpinner();




        edit = findViewById(R.id.editBtn);
        cancel = findViewById(R.id.cancelBtn);
        edit.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        /*playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //when play is clicked show stop button and hide play button
                playButton.setVisibility(View.GONE);
                stopButton.setVisibility(View.VISIBLE);
            }
        });*/
    }
    public void add(View view) {
        BookInfo bookInfo = new BookInfo();

        if( TextUtils.isEmpty(bookName.getText())){
           bookName.setError( "Book name is required!" );
        }
        if(TextUtils.isEmpty(author.getText())){
            author.setError( "Author name is required!" );
        }
        if(TextUtils.isEmpty(quantity.getText())){
            quantity.setError( "Quantity is required!" );
        }
       /* if(TextUtils.isEmpty(publisher.getSelectedItem().toString())){
            publisher.setError( "Quantity is required!" );
        }*/
        if(TextUtils.isEmpty(price.getText())){
            price.setError( "Price is required!" );
        }
        else {
            bookInfo.setBookName(bookName.getText().toString());
            bookInfo.setAuthor(author.getText().toString());
            bookInfo.setEdition(edition.getText().toString());
            bookInfo.setPublisherInfoList(publisher.getSelectedItem().toString());
            bookInfo.setQuantity(Integer.valueOf(quantity.getText().toString()));
            bookInfo.setPrice(price.getText().toString());
//            bookInfo.setCountry(country.getText().toString());
            bookInfo.setLanguage(language.getText().toString());
            long insertedRow = bookInfoManager.addBookInfo(bookInfo);
            if (insertedRow > 0) {
                Toast.makeText(this, "" + insertedRow, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
            }
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
