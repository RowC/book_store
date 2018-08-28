package com.bitm.rc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.SalesInfoManager;
import com.bitm.rc.bookstore.controller.SalesListAdapter;
import com.bitm.rc.bookstore.model.BookSales;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class SalesInfoListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private SalesListAdapter salesListAdapter;
    SalesInfoManager salesInfoManager = new SalesInfoManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_info_list);
        recyclerView = findViewById(R.id.sales_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<BookSales> bookInfoList = salesInfoManager.getSalesInfoList();

        List<BookSales> bookSalesInfos = new ArrayList<BookSales>();
        Integer id;String salesPrice,salesDate,bookInfoId; Integer salesQty;
        for (BookSales info : bookInfoList) {
            id= info.getId();
            bookInfoId = (info.getBookInfoId().toString());
            salesQty = Integer.valueOf(info.getSalesQty());
            salesPrice = info.getSalesPrice().toString();
            salesDate = info.getSalesDate().toString();
            BookSales bookSales = new BookSales(id, salesQty,salesPrice,salesDate,bookInfoId);
            bookSalesInfos.add(bookSales);
        }

        salesListAdapter = new SalesListAdapter(bookSalesInfos, this);
        recyclerView.setAdapter(salesListAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        return;
    }
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
        salesListAdapter.getFilter().filter(newText);
        return true;
    }
}
