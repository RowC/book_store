package com.bitm.rc.bookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.UserInfoListAdapter;
import com.bitm.rc.bookstore.controller.UserInfoManager;
import com.bitm.rc.bookstore.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class UserInfoListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserInfoListAdapter userInfoListAdapter;
    UserInfoManager userInfoManager = new UserInfoManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);


        recyclerView = findViewById(R.id.user_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<UserInfo> userInfoList = userInfoManager.getUserInfoList();

        List<UserInfo> userInfos = new ArrayList<UserInfo>();
        Integer id;String userFullName,userName,userPass;
        for (UserInfo info : userInfoList) {
            userFullName = (info.getUserFullName());
            userName = info.getUserName().toString();
            userPass = info.getPassword().toString();
            UserInfo userInfo = new UserInfo(userFullName,userName,userPass);
            userInfos.add(userInfo);
        }

        userInfoListAdapter = new UserInfoListAdapter(userInfos, this);
        recyclerView.setAdapter(userInfoListAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        return;
    }
}
