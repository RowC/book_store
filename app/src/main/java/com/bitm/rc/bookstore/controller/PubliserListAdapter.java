package com.bitm.rc.bookstore.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.model.PublisherInfo;

import java.util.List;

public class PubliserListAdapter extends RecyclerView.Adapter<PubliserListAdapter.PublisherViewHolder> {
  public  List<PublisherInfo> publisherInfoList;

    @NonNull
    @Override
    public PublisherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.publiser_list, parent, false);
        return new PublisherViewHolder(v);
    }

    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    public void onBindViewHolder(PublisherViewHolder holder, int position) {
        PublisherInfo publisherInfo = publisherInfoList.get(position);
        holder.textViewPname.setText("Book Id : " + publisherInfo.getName());
        holder.textViewAddress.setText("Book Name : " + publisherInfo.getAddress());
    }

    @Override
    public int getItemCount() {
        return publisherInfoList.size();
    }

    public class PublisherViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewPname;
        public TextView textViewAddress;
        public ImageView imgEdit;
        public ImageView imgDelete;
        private int pos;
        public PublisherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDlt);
            textViewPname = itemView.findViewById(R.id.pNameTv);
            textViewAddress = itemView.findViewById(R.id.pAddressTv);
        }
    }
}
