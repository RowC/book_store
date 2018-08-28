package com.bitm.rc.bookstore.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.MainActivity;
import com.bitm.rc.bookstore.ProfileActivity;
import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.UserInfo;

import java.util.List;

public class UserInfoListAdapter extends RecyclerView.Adapter<UserInfoListAdapter.ViewHolder> {
    List<UserInfo> userInfoList;
    UserInfoManager userInfoManager;
    Context context;

    public UserInfoListAdapter(List<UserInfo> userInfoList, Context context) {
        this.userInfoList = userInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserInfoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull UserInfoListAdapter.ViewHolder holder, int position) {
        UserInfo userInfo = userInfoList.get(position);
        holder.textViewFullName.setText("Full Name : " + userInfo.getUserFullName());
        holder.textViewUserName.setText("User Name : " + userInfo.getUserName());
        holder.textViewPassword.setText("Password : " + userInfo.getPassword());
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewFullName;
        public TextView textViewUserName;
        public TextView textViewPassword;
        public ImageView imgUserEdit;
        public ImageView imgUserDelete;
        private int pos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUserEdit = itemView.findViewById(R.id.imgUserEdit);
            imgUserDelete = itemView.findViewById(R.id.imgUserDlt);
            textViewFullName = itemView.findViewById(R.id.txtFullName);
            textViewUserName = itemView.findViewById(R.id.txtUserName);
            textViewPassword = itemView.findViewById(R.id.txtPassword);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgUserEdit:
                    pos = getAdapterPosition();
                    editSalesInfoDialog(pos);
                    break;
                case R.id.imgUserDlt:
                    pos = getAdapterPosition();
                    removeItem(pos);
//                    notifyItemRemoved(this.getLayoutPosition());
                    break;
            }
        }

        public void setListener() {
            imgUserEdit.setOnClickListener(UserInfoListAdapter.ViewHolder.this);
            imgUserDelete.setOnClickListener(UserInfoListAdapter.ViewHolder.this);

        }
    }

    public void editSalesInfoDialog(final int position) {
        final Dialog userInfoDiloag = new Dialog(context);
        userInfoDiloag.setCanceledOnTouchOutside(true);
//        userInfoDiloag.setTitle("Edit");
        userInfoDiloag.setContentView(R.layout.activity_user_info);

        UserInfo info = userInfoList.get(position);
        userInfoManager = new UserInfoManager(context);
//        final int userInfoId = info.getId();

       final TextView mgs = userInfoDiloag.findViewById(R.id.tvErrorMsg);
       final TextView textViewEditTitle = userInfoDiloag.findViewById(R.id.tvEditTitle);
        mgs.setVisibility(View.GONE);
        textViewEditTitle.setVisibility(View.VISIBLE);

//        final EditText id = userInfoDiloag.findViewById(R.id.user_id);
        final EditText fullName = userInfoDiloag.findViewById(R.id.full_name);
        final EditText userName = userInfoDiloag.findViewById(R.id.user_name);
        final EditText pass = userInfoDiloag.findViewById(R.id.password);

        Button editSales = userInfoDiloag.findViewById(R.id.btnUserSave);
        Button cancelSales = userInfoDiloag.findViewById(R.id.btnUserCancel);


//        id.setText(info.getId());
        fullName.setText(info.getUserFullName());
        userName.setText(info.getUserName());
        pass.setText(info.getPassword());

        userInfoDiloag.show();

        editSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(fullName.getText())) {
                    fullName.setError("Full name is required!");
                }
                if (TextUtils.isEmpty(userName.getText())) {
                    userName.setError("User namw is required!");
                }
                if (TextUtils.isEmpty(pass.getText())) {
                    pass.setError("Password is required!");
                } else {
                    UserInfoListAdapter userInfoListAdapter = new UserInfoListAdapter(userInfoList, context);
                    UserInfo userInfo = userInfoList.get(position);

                    userInfo.setUserFullName(fullName.getText().toString());
                    userInfo.setUserName(userName.getText().toString());
                    userInfo.setPassword(pass.getText().toString());
                    long insertedRow = userInfoManager.editUserInfo(userInfo);
                    if (insertedRow < 1) {
                        userInfoListAdapter.notifyItemChanged(position);
                    } else {
                        Toast.makeText(context, "something went wrong!!!", Toast.LENGTH_SHORT).show();
                    }

                    userInfoDiloag.dismiss();
                }
            }
        });
        cancelSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfoDiloag.cancel();
            }
        });
    }

    public void removeItem(int position) {
        UserInfo info = userInfoList.get(position);
        userInfoManager = new UserInfoManager(context);
//        int id = info.getId();
        String id = info.getUserName();
        if (userInfoManager.delete(id)) {
            userInfoList.remove(position);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
//            this.notifyItemRemoved(position);

        } else {
            Toast.makeText(context, "Unable To Delete", Toast.LENGTH_LONG).show();
        }

//        bookInfoListsFull.clear();
        this.notifyItemRangeChanged(position, userInfoList.size());
    }
}
