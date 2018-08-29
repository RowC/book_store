package com.bitm.rc.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.BookInfoManager;
import com.bitm.rc.bookstore.controller.SalesInfoManager;

public class ProfileActivity extends AppCompatActivity {
    TextView totalStock;
    TextView totalItem;
    TextView totalSales;
    TextView remainingStock;
    BookInfoManager bookInfoManager  =new BookInfoManager(this);
    SalesInfoManager salesInfoManager  =new SalesInfoManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        totalStock=findViewById(R.id.totalStockTv);
        totalItem=findViewById(R.id.totalItemTv);
        totalSales=findViewById(R.id.totalSalesTv);
        remainingStock=findViewById(R.id.totalRemainingTv);

        totalStock.setText("Total Stock "+bookInfoManager.getTotalStock());
        totalItem.setText(bookInfoManager.getTotalItem());
        totalSales.setText( "Total Sales "+salesInfoManager.getTotalSales());
        remainingStock.setText("Remaining Stock "+(bookInfoManager.getTotalStock()-salesInfoManager.getTotalSales()));

    }

    public void gotoPublisherInfo(View view) {
        Intent intent = new Intent(this,PublisherActivity.class);
        startActivity(intent);
    }
    public void gotoBookInfo(View view) {
        Intent intent = new Intent(this,BookInfoActivity.class);
        startActivity(intent);
    }
    public void bookInfoList(View view) {
        Intent intent = new Intent(this,BookInfoListActivity.class);
        startActivity(intent);
    }
    public void salesInfoList(View view) {
        Intent intent = new Intent(this,SalesInfoListActivity.class);
        startActivity(intent);
    }
    public void userInfoList(View view) {
        Intent intent = new Intent(this,UserInfoListActivity.class);
        startActivity(intent);
    }
}
