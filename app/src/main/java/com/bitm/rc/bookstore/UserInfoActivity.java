package com.bitm.rc.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.controller.UserInfoManager;
import com.bitm.rc.bookstore.model.UserInfo;

public class UserInfoActivity extends AppCompatActivity {
    EditText fullName;
    EditText userName;
    EditText password;
    TextView textViewEditTitle;
    TextView mgs;
    Button btnCancel;

    UserInfoManager userInfoManager = new UserInfoManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        fullName = findViewById(R.id.full_name);
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        btnCancel = findViewById(R.id.btnUserCancel);
        textViewEditTitle = findViewById(R.id.tvEditTitle);
        mgs = findViewById(R.id.tvErrorMsg);
        textViewEditTitle.setVisibility(View.GONE);
        mgs.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
    }

    public void submit(View view) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserFullName(fullName.getText().toString());
        userInfo.setUserName(userName.getText().toString());
        userInfo.setPassword(password.getText().toString());
        long insertedRow = userInfoManager.addUser(userInfo);

        /*if (insertedRow > 1) {
            mgs.setVisibility(View.VISIBLE);
            mgs.setText("One user already exist. You can not create more than one user!!!");
            Toast.makeText(this, "One user already exist. You can not create more than one user!!!", Toast.LENGTH_SHORT).show();
        } else*/ if (insertedRow !=2) {
            Intent intent = new Intent(this, SucessActivity.class);
            intent.putExtra("userFullName", "Welcome " + fullName.getText().toString());
            intent.putExtra("userName", "Your user name is " + userName.getText().toString());
            intent.putExtra("and", "&");
            intent.putExtra("password", "Password is " + password.getText().toString());
            startActivity(intent);
            return;
        } else {
            mgs.setVisibility(View.VISIBLE);
            mgs.setText("One user already exist. You can not create more than one user!!!");
            Toast.makeText(this, "One user already exist. You can not create more than one user!!!", Toast.LENGTH_SHORT).show();

//            mgs.setText("something went wrong!!!");
//            Toast.makeText(this, "something went wrong!!!", Toast.LENGTH_SHORT).show();
        }
    }


}
