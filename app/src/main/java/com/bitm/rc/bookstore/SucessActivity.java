package com.bitm.rc.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bitm.rc.book_store.R;

public class SucessActivity extends AppCompatActivity {
TextView textViewFullName,textViewUserName,textViewPassword,textViewAnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
        textViewFullName=findViewById(R.id.tvFullName);
        textViewUserName=findViewById(R.id.tvUserName);
        textViewPassword=findViewById(R.id.tvPass);
        textViewAnd=findViewById(R.id.tvAnd);

        textViewFullName.setText(getIntent().getStringExtra("userFullName"));
        textViewUserName.setText(getIntent().getStringExtra("userName"));
        textViewPassword.setText(getIntent().getStringExtra("password"));
        textViewAnd.setText(getIntent().getStringExtra("and"));
    }

    public void gotoHome(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
