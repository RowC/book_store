package com.bitm.rc.bookstore.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.model.BookInfo;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
 List<BookInfo> bookInfoLists;
    Context context;
    public BookListAdapter(List<BookInfo> bookInfoLists,Context context) {
        this.bookInfoLists = bookInfoLists;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookInfo bookInfo = bookInfoLists.get(position);
        holder.textViewBookName.setText("Book Name : "+bookInfo.getBookName());
        holder.textViewAuthorName.setText("Author : "+bookInfo.getAuthor());
        holder.textViewEditionName.setText("Edition : "+bookInfo.getEdition());
        holder.textViewQuantity.setText("Quantity : "+bookInfo.getQuantity().toString());
        holder.textViewPrice.setText("Price : "+bookInfo.getPrice());
    }

    @Override
    public int getItemCount() {
        return bookInfoLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBookName;
        public TextView textViewAuthorName;
        public TextView textViewEditionName;
        public TextView textViewQuantity;
        public TextView textViewPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewBookName = itemView.findViewById(R.id.bookTv);
            textViewAuthorName = itemView.findViewById(R.id.authorTv);
            textViewEditionName = itemView.findViewById(R.id.editionTv);
            textViewQuantity = itemView.findViewById(R.id.qtyTv);
            textViewPrice = itemView.findViewById(R.id.priceTv);
        }
    }
}
