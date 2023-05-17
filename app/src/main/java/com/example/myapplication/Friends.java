package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    Button add, search, modify, view, delete, deleteAll, back;
    ArrayList<String> id, name, phone, bday;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        add = findViewById(R.id.addFriends);
        search = findViewById(R.id.searchFriends);
        modify = findViewById(R.id.modifyFriends);
        view = findViewById(R.id.viewFriends);
        delete = findViewById(R.id.deleteFriends);
        deleteAll = findViewById(R.id.deleteAllFriends);
        back = findViewById(R.id.backFriends);

        db = new DbHelper(Friends.this);

        id = new ArrayList<>();
        name = new ArrayList<>();
        phone = new ArrayList<>();
        bday = new ArrayList<>();

        add.setOnClickListener(view -> {
            Intent intent = new Intent(Friends.this, AddFriend.class);
            startActivity(intent);
        });

        view.setOnClickListener(view -> {

            Cursor cursor = db.readAllData();

            while (cursor.moveToNext()){

                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                phone.add(cursor.getString(2));
                bday.add(cursor.getString(3));

            }

            System.out.println( name.toString() );

        });

        back.setOnClickListener(view -> finish());

    }
}