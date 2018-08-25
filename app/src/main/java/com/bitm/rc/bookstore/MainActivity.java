package com.bitm.rc.bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bitm.rc.book_store.R;
import com.bitm.rc.bookstore.db.DatabaseHelper;
import com.bitm.rc.bookstore.model.BookInfo;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
    }


    public void signIn(View view) {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {"user_name", "password"};
        String[] cValues = {userName.getText().toString(), password.getText().toString()};
        Cursor cursor = db.query(helper.TABLE_NAME, columns, "user_name = ? AND password = ?", cValues, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this,"User name and password not matched",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void signUp(View view) {
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }
}
