package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddFriend extends AppCompatActivity {

    EditText name, phone, bday;
    Button add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        name = findViewById(R.id.nameAdd);
        phone = findViewById(R.id.phoneAdd);
        bday = findViewById(R.id.bdayAdd);

        add = findViewById(R.id.addFrd);
        cancel = findViewById(R.id.cancelFrd);

        add.setOnClickListener(view -> {

            DbHelper db = new DbHelper(AddFriend.this);
            db.addFriend(name.getText().toString().trim(),
                    phone.getText().toString().trim(),
                    bday.getText().toString().trim());

        });
        cancel.setOnClickListener(view -> finish());

    }
}