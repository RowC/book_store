package com.bitm.rc.bookstore.controller;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.BookSales;

import java.util.ArrayList;
import java.util.List;

public class SalesListAdapter extends RecyclerView.Adapter<SalesListAdapter.ViewHolder> implements Filterable {
    List<BookSales> salesInfoLists;
    List<BookSales> salesInfoListsFull;
    Context context;
    SalesInfoManager salesInfoManager;


    public SalesListAdapter(List<BookSales> salesInfoLists, Context context) {
        this.salesInfoLists = salesInfoLists;
        this.salesInfoListsFull = new ArrayList<>(salesInfoLists);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_list, parent, false);
        return new SalesListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookSales bookSales = salesInfoLists.get(position);
       /* String bookName=null;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM book_info WHERE book_id = "+bookSales.getBookInfoId(), null);
        if (c.moveToFirst()) {
            do {
                bookName = c.getString(c.getColumnIndex(databaseHelper.BOOK_NAME));

            } while (c.moveToNext());
        }
        c.close();*/

        holder.textViewSalesId.setText("Sales Id : " + bookSales.getId());
        holder.textViewSalesQty.setText("Sales Quantity : " + bookSales.getSalesQty());
        holder.textViewSalesPrice.setText("Sales Price : " + bookSales.getSalesPrice());
        holder.textViewSalesDate.setText("Sales Date : " + bookSales.getSalesDate());
        holder.textViewBookId.setText("Book Name : " + bookSales.getBookInfoId());
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return salesInfoLists.size();
    }

    /**********Search view*******/

    @Override
    public Filter getFilter() {
        return salesFilter;
    }

    private Filter salesFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<BookSales> filterList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(salesInfoLists);
            } else {
                String filterPatern = charSequence.toString().toLowerCase().trim();
                for (BookSales bookInfo : salesInfoLists) {
                    if (bookInfo.getBookInfoId().toLowerCase().contains(filterPatern)) {
                        filterList.add(bookInfo);
                    }/* else {
                        salesInfoLists.clear();
                    }*/
                }

            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            salesInfoLists.clear();
            salesInfoLists.addAll((List) filterResults.values);

           /* salesInfoListsFull.clear();
            salesInfoListsFull.addAll((List) filterResults.values);*/
            notifyDataSetChanged();
        }
    };

    /*==============================End===============================*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewSalesId;
        public TextView textViewSalesQty;
        public TextView textViewSalesPrice;
        public TextView textViewSalesDate;
        public TextView textViewBookId;
        public ImageView imgEdit;
        private int pos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEdit = itemView.findViewById(R.id.imgSalesEdit);
//            imgDelete = itemView.findViewById(R.id.imgDlt);
            textViewSalesId = itemView.findViewById(R.id.salesIdTv);
            textViewSalesQty = itemView.findViewById(R.id.salesQtyTv);
            textViewSalesPrice = itemView.findViewById(R.id.salesPriceTv);
            textViewSalesDate = itemView.findViewById(R.id.salesDateTv);
            textViewBookId = itemView.findViewById(R.id.BookNameTv);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgSalesEdit:
                    pos = getAdapterPosition();
                    editSalesInfoDialog(pos);
                    break;
            }
        }

        public void setListener() {
            imgEdit.setOnClickListener(SalesListAdapter.ViewHolder.this);

        }
    }

    public void editSalesInfoDialog(final int position) {
        final Dialog salesDiloag = new Dialog(context);
        salesDiloag.setTitle("SQLite Database");
        salesDiloag.setContentView(R.layout.activity_sales_info);
        /*DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = databaseHelper.getReadableDatabase();
*/
        BookSales info = salesInfoLists.get(position);
        salesInfoManager = new SalesInfoManager(context);
//        final int bookInfoId = info.getId();
        final EditText bookInfoId = salesDiloag.findViewById(R.id.txtBookName);
        final EditText salesQty = salesDiloag.findViewById(R.id.txtSalesQty);
        final EditText salesPrice = salesDiloag.findViewById(R.id.txtSalesPrice);
        final EditText salesDate = salesDiloag.findViewById(R.id.txtSalesDate);
        final EditText bookInfoName = salesDiloag.findViewById(R.id.txtBookName);
        Button editSales = salesDiloag.findViewById(R.id.btnSalesSave);
        Button cancelSales = salesDiloag.findViewById(R.id.btnSalesCancel);

       /* String bookName=null;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM book_info WHERE book_id = "+info.getBookInfoId(), null);
        if (c.moveToFirst()) {
            do {
                bookName = c.getString(c.getColumnIndex(databaseHelper.BOOK_NAME));

            } while (c.moveToNext());
        }
        c.close();*/
        bookInfoName.setText(info.getBookInfoId().toString());
        salesQty.setText(info.getSalesQty().toString());
        salesPrice.setText(info.getSalesPrice().toString());
        salesDate.setText(info.getSalesDate().toString());

        salesDiloag.show();

        editSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bookInfoName.getText())) {
                    bookInfoName.setError("Book name is required!");
                }
                if (TextUtils.isEmpty(salesQty.getText())) {
                    salesQty.setError("Sales Quantity is required!");
                }
                if (TextUtils.isEmpty(salesPrice.getText())) {
                    salesPrice.setError("Sales Price is required!");
                }
                if (TextUtils.isEmpty(salesDate.getText())) {
                    salesDate.setError("Sales Date is required!");
                } else {
                    SalesListAdapter adapter = new SalesListAdapter(salesInfoLists, context);
                    BookSales bookSales = salesInfoLists.get(position);

                    bookSales.setSalesQty(Integer.valueOf(salesQty.getText().toString()));
                    bookSales.setSalesPrice(salesPrice.getText().toString());
                    bookSales.setSalesDate(salesDate.getText().toString());
                    bookSales.setBookInfoId(bookInfoId.getText().toString());
                    long insertedRow = salesInfoManager.editSalesInfo(bookSales);
                    if (insertedRow > 0) {
                        adapter.notifyItemChanged(position);
                    } else {
                        Toast.makeText(context, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                    }

                    salesDiloag.dismiss();
                }
            }
        });
        cancelSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salesDiloag.cancel();
            }
        });
    }


}
