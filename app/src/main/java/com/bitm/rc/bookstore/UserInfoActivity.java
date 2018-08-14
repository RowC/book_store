package com.bitm.rc.bookstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.db.UserInfoManager;
import com.bitm.rc.bookstore.model.UserInfo;

public class UserInfoActivity extends AppCompatActivity {
EditText fullName;
EditText userName;
EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        fullName=findViewById(R.id.full_name);
        userName=findViewById(R.id.user_name);
        password=findViewById(R.id.password);
    }

    public void submit(View view) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserFullName(fullName.getText().toString());
        userInfo.setUserName(userName.getText().toString());
        userInfo.setPassword(password.getText().toString());
        UserInfoManager userInfoManager= new UserInfoManager(this);
        long insertedRow=userInfoManager.addUser(userInfo);
        if(insertedRow>0){
            Toast.makeText(this, ""+insertedRow, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }



}
