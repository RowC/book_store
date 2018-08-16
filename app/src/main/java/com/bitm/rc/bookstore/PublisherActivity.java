package com.bitm.rc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.PublisherInfoManager;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.Date;

public class PublisherActivity extends AppCompatActivity {
EditText name;
EditText address;
EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
        name= findViewById(R.id.publisher_name);
        address= findViewById(R.id.publisher_address);
    }

    public void add(View view) {
        PublisherInfo publisherInfo = new PublisherInfo();
        publisherInfo.setName(name.getText().toString());
        publisherInfo.setAddress(address.getText().toString());
        publisherInfo.setDate(new Date());
        PublisherInfoManager infoManager= new PublisherInfoManager(this);
        long insertedRow=infoManager.addPublisher(publisherInfo);
        if(insertedRow>0){
            Toast.makeText(this, ""+insertedRow, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
