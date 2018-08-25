package com.bitm.rc.bookstore.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.model.BookInfo;
import com.bitm.rc.bookstore.model.BookSales;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.ArrayList;
import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> implements Filterable {
    List<BookInfo> bookInfoLists;
    List<BookInfo> bookInfoListsFull;
    Context context;
    BookInfoManager bookInfoManager;
    SalesInfoManager salesInfoManager;

    public BookListAdapter(List<BookInfo> bookInfoLists, Context context) {
        this.bookInfoLists = bookInfoLists;
        this.bookInfoListsFull = new ArrayList<>(bookInfoLists);
        this.context = context;
    }

    public void removeItem(int position) {
        BookInfo info = bookInfoListsFull.get(position);
        bookInfoManager = new BookInfoManager(context);
        int id = info.getId();
        if (bookInfoManager.delete(id)) {
            bookInfoListsFull.remove(position);
            this.notifyItemRemoved(position);
        } else {
            Toast.makeText(context, "Unable To Delete", Toast.LENGTH_LONG).show();
        }

//        bookInfoListsFull.clear();
        this.notifyItemRangeChanged(position, bookInfoListsFull.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewbookId;
        public TextView textViewBookName;
        public TextView textViewAuthorName;
        public TextView textViewEditionName;
        public TextView textViewQuantity;
        public TextView textViewPrice;
        public ImageView imgEdit;
        public ImageView imgDelete;
        public ImageView imgSales;
        private int pos;
        public TextView textViewPublisher;
        public TextView textViewLanguage;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDlt);
            imgSales = itemView.findViewById(R.id.imgSls);
            textViewbookId = itemView.findViewById(R.id.bookIdTv);
            textViewBookName = itemView.findViewById(R.id.bookTv);
            textViewAuthorName = itemView.findViewById(R.id.authorTv);
            textViewEditionName = itemView.findViewById(R.id.editionTv);
            textViewQuantity = itemView.findViewById(R.id.qtyTv);
            textViewPrice = itemView.findViewById(R.id.priceTv);
            textViewPublisher = itemView.findViewById(R.id.publisherTv);
            textViewLanguage = itemView.findViewById(R.id.lanTv);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgDlt:
                    pos = getAdapterPosition();
                    removeItem(pos);
//                    notifyItemRemoved(this.getLayoutPosition());
                    break;
                case R.id.imgEdit:
                    pos = getAdapterPosition();
                    displayDialog(pos);
                    break;
                case R.id.imgSls:
//                    Toast.makeText(context,"hhh",Toast.LENGTH_LONG).show();
                    pos = getAdapterPosition();
                    displaySalesInfoDialog(pos);
                    break;
            }
        }

        public void setListener() {
            imgEdit.setOnClickListener(ViewHolder.this);
            imgDelete.setOnClickListener(ViewHolder.this);
            imgSales.setOnClickListener(ViewHolder.this);
        }

        /*@Override
        public void onClick(View view) {

        }*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoLists.get(position);
        holder.textViewbookId.setText("Book Id : " + bookInfo.getId());
        holder.textViewBookName.setText("Book Name : " + bookInfo.getBookName());
        holder.textViewAuthorName.setText("Author : " + bookInfo.getAuthor());
        holder.textViewEditionName.setText("Edition : " + bookInfo.getEdition());
        holder.textViewPublisher.setText("Publisher's : " + bookInfo.getPublisherInfoList());
        holder.textViewQuantity.setText("Quantity : " + bookInfo.getQuantity().toString());
        holder.textViewPrice.setText("Price : " + bookInfo.getPrice());
        holder.textViewLanguage.setText("Language : " + bookInfo.getLanguage());
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return bookInfoListsFull.size();
    }

    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    private Filter bookFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<BookInfo> filterList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterList.addAll(bookInfoListsFull);
            } else {
                String filterPatern = charSequence.toString().toLowerCase().trim();
                for (BookInfo bookInfo : bookInfoListsFull) {
                    if (bookInfo.getBookName().toLowerCase().contains(filterPatern)) {
                        filterList.add(bookInfo);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bookInfoLists.clear();
            bookInfoLists.addAll((List) filterResults.values);

           /* bookInfoListsFull.clear();
            bookInfoListsFull.addAll((List) filterResults.values);*/
            notifyDataSetChanged();
        }
    };



   /* public void removeItem(View view) {
//    int possition = bookInfoListsFull.indexOf(bookInfo);
//        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        int possition = new ViewHolder(view).getAdapterPosition();
        BookInfo info = bookInfoListsFull.get(possition);
        bookInfoManager = new BookInfoManager(context);
        int id = info.getId();
        if (bookInfoManager.delete(id)) {
            bookInfoListsFull.remove(possition);
            notifyItemRemoved(possition);
//            notifyDataSetChanged();
            notifyItemRangeChanged(possition, bookInfoListsFull.size());
        } else {
            Toast.makeText(context, "Unable To Delete", Toast.LENGTH_LONG).show();
        }


//    notifyDataSetChanged();
    }*/

    /*public void deleteItem(View view) {
        Button bt =(Button)view;
        String del_id=bt.getTag().toString();
        //adapter.removeItem();
bookInfoManager.delete(Integer.parseInt(del_id));
        for(int i=0;i<bookInfoListsFull.size();i++){
            if(!bookInfoListsFull.get(i).getId().equals(null)){
                bookInfoListsFull.remove(i);
            }

        }
        //notify listview of dataset changed
        notifyDataSetChanged();

    }*/
   /* private void displayDialog(int pos, View View)
    {

        Dialog d=new Dialog(context);
        d.setTitle("SQLite Database");
        d.setContentView(R.layout.activity_book_info);
        BookInfo info = bookInfoListsFull.get(pos);
        bookInfoManager = new BookInfoManager(context);
        int id = info.getId();
        String  bookName = info.getBookName();
       *//* nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
//        retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(nameEditText.getText().toString());
            }
        });
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookInfoManager.getBookList();
            }
        });*//*

        d.show();
    }*/
    public void displayDialog(final int position) {
        final  Spinner publisher;
       final Dialog d = new Dialog(context);
        d.setTitle("SQLite Database");
        d.setContentView(R.layout.activity_book_info);
//        BookInfo info = bookInfoListsFull.get(position);
        BookInfo info = bookInfoLists.get(position);
        bookInfoManager = new BookInfoManager(context);
        String id = info.getId().toString();
        String bookName = info.getBookName();
        String bookAuthor = info.getAuthor();
        String bookPublisher = info.getPublisherInfoList();
        String bookEdition = info.getEdition();
        String bookQty = info.getQuantity().toString();
        String bookPrice = info.getPrice();
        String bookLanguage = info.getLanguage();
       final EditText bookId = d.findViewById(R.id.book_id);
        final EditText bookTxt = d.findViewById(R.id.book_name);
        final EditText author = d.findViewById(R.id.author_name);
        final  EditText edition = d.findViewById(R.id.edition);
        final  EditText quantity = d.findViewById(R.id.qty);
        final  EditText price = d.findViewById(R.id.price);
        final  EditText language = d.findViewById(R.id.language);
        Button save = d.findViewById(R.id.saveBtn);
        Button edit = d.findViewById(R.id.editBtn);
        Button cancel = d.findViewById(R.id.cancelBtn);
        save.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);
        bookId.setText(id);
        bookTxt.setText(bookName.toString());
        author.setText(bookAuthor);
        edition.setText(bookEdition);
        quantity.setText(bookQty);
        price.setText(bookPrice);
        language.setText(bookLanguage);
        /*******Spinner*********/
        publisher = d.findViewById(R.id.publisher);
        ArrayAdapter<PublisherInfo> dataAdapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item, bookInfoManager.addItems());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        publisher.setAdapter(dataAdapter);
        for (int i = 0; i < publisher.getCount(); i++) {
            if (publisher.getItemAtPosition(i).equals(bookPublisher)) {
                publisher.setSelection(i);
                break;
            }
        }
        d.show();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(bookTxt.getText())) {
                    bookTxt.setError("Book name is required!");
                }
                if (TextUtils.isEmpty(author.getText())) {
                    author.setError("Author name is required!");
                }
                if (TextUtils.isEmpty(quantity.getText())) {
                    quantity.setError("Quantity is required!");
                }
       /* if(TextUtils.isEmpty(publisher.getSelectedItem().toString())){
            publisher.setError( "Quantity is required!" );
        }*/
                if (TextUtils.isEmpty(price.getText())) {
                    price.setError("Price is required!");
                } else {
//                   BookListAdapter adapter =new BookListAdapter(bookInfoListsFull,context);
                   BookListAdapter adapter =new BookListAdapter(bookInfoLists,context);
                    BookInfo info = bookInfoLists.get(position);
                    info.setBookName(bookTxt.getText().toString());
                    info.setAuthor(author.getText().toString());
                    info.setEdition(edition.getText().toString());
                    info.setPublisherInfoList(publisher.getSelectedItem().toString());
                    info.setQuantity(Integer.valueOf(quantity.getText().toString()));
                    info.setPrice(price.getText().toString());
                    info.setLanguage(language.getText().toString());
                    long insertedRow = bookInfoManager.editBookInfo(info);
                    if (insertedRow > 0) {
                        adapter.notifyItemChanged(position);
//                        bookInfoManager.getBookList();
                    } else {
                        Toast.makeText(context, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                    }
                    d.dismiss();


                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.cancel();
            }
        });
    }
    public void displaySalesInfoDialog(final int position) {

//        Toast.makeText(context,"hhh",Toast.LENGTH_LONG).show();
       final Dialog salesDiloag = new Dialog(context);
        salesDiloag.setTitle("SQLite Database");
        salesDiloag.setContentView(R.layout.activity_sales_info);


        BookInfo info = bookInfoLists.get(position);
        bookInfoManager = new BookInfoManager(context);
        salesInfoManager = new SalesInfoManager(context);
       final int bookInfoId = info.getId();

       final EditText salesQty = salesDiloag.findViewById(R.id.txtSalesQty);
        final EditText salesPrice = salesDiloag.findViewById(R.id.txtSalesPrice);
        final EditText salesDate = salesDiloag.findViewById(R.id.txtSalesDate);
        final  EditText bookInfoName = salesDiloag.findViewById(R.id.txtBookName);
        Button saveSales = salesDiloag.findViewById(R.id.btnSalesSave);
        Button cancelSales = salesDiloag.findViewById(R.id.btnSalesCancel);
        bookInfoName.setText(info.getBookName());
        salesPrice.setText(info.getPrice());

        salesDiloag.show();

        saveSales.setOnClickListener(new View.OnClickListener() {
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
                   BookSales bookSales = new BookSales();
                    bookSales.setSalesQty(Integer.valueOf(salesQty.getText().toString()));
                    bookSales.setSalesPrice(salesPrice.getText().toString());
                    bookSales.setSalesDate(salesDate.getText().toString());
                    bookSales.setBookInfoId(bookInfoId);
                    long insertedRow = salesInfoManager.addSalesInfo(bookSales);
                    if (insertedRow > 0) {
                        salesDiloag.dismiss();
                    } else {
                        Toast.makeText(context, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                    }
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
