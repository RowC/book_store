package com.bitm.rc.bookstore;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.BookInfoManager;
import com.bitm.rc.bookstore.controller.BookListAdapter;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class BookInfoListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private BookListAdapter adapter;
    BookInfoManager bookInfoManager = new BookInfoManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_list);
        recyclerView = findViewById(R.id.book_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        bookInfoManager = new BookInfoManager(this);
        ArrayList<BookInfo> bookInfoList = bookInfoManager.getBookList();
//        ArrayList<String>listForDisplay=new ArrayList<>();

        List<BookInfo> bookInfos = new ArrayList<BookInfo>();
        Integer id;String bName,aName,edition,publisher,lan, price; Integer qty;
        for (BookInfo info : bookInfoList) {
             id= info.getId();
            bName = info.getBookName().toString();
            aName = info.getAuthor().toString();
            edition = info.getEdition().toString();
            publisher = info.getPublisherInfoList().toString();
            qty = Integer.valueOf(info.getQuantity());
            price = (info.getPrice());
            lan = info.getLanguage().toString();
            BookInfo bookInfo = new BookInfo(id, bName, aName, edition,publisher, qty, price,lan);
            bookInfos.add(bookInfo);
        }

        adapter = new BookListAdapter(bookInfos, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return true;
    }



    /**********end**************/

}
