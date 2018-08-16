package com.bitm.rc.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.model.BookInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoPublisherInfo(View view) {
        Intent intent = new Intent(this,PublisherActivity.class);
        startActivity(intent);
    }

    public void gotoBookInfo(View view) {
        Intent intent = new Intent(this,BookInfoActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
    }
    public void signUp(View view) {
        Intent intent = new Intent(this,UserInfoActivity.class);
        startActivity(intent);
    }
    public void bookInfoList(View view) {
        Intent intent = new Intent(this,BookInfoListActivity.class);
        startActivity(intent);
    }
}
