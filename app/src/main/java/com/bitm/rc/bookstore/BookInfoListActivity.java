package com.bitm.rc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.BookInfoManager;
import com.bitm.rc.bookstore.controller.BookListAdapter;
import com.bitm.rc.bookstore.model.BookInfo;

import java.util.ArrayList;
import java.util.List;

public class BookInfoListActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
BookInfoManager bookInfoManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_list);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookInfoManager = new BookInfoManager(this);
        ArrayList<BookInfo>bookInfoList=bookInfoManager.getBookList();
//        ArrayList<String>listForDisplay=new ArrayList<>();

        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        for(BookInfo info:bookInfoList){
            //BookInfo bookInfo = new BookInfo(1,"Book 1"+i+1,"Author 1","1st","120/pc",100);
            Integer id = info.getId();
            String bName = info.getBookName().toString();
            String aName = info.getAuthor().toString();
            String edition = info.getEdition().toString();
            String qty = info.getQuantity().toString();
            Integer price = Integer.valueOf(info.getPrice());
            BookInfo bookInfo = new BookInfo(bName,aName,edition,qty,price);
            bookInfos.add(bookInfo);
        }
        adapter= new BookListAdapter(bookInfos,this);
        recyclerView.setAdapter(adapter);
    }
}
