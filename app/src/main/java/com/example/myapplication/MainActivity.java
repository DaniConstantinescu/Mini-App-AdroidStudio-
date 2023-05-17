package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button notes = (Button) findViewById(R.id.addNotes);
        Button friends = (Button) findViewById(R.id.viewNotes);
        Button school = (Button) findViewById(R.id.deleteNotes);
        Button close = (Button) findViewById(R.id.backNotes);



        notes.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Notes.class);
            startActivity(intent);
        });

        friends.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Friends.class);
            startActivity(intent);
        });

        school.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, School.class);
            startActivity(intent);
        });

        close.setOnClickListener(view -> finish());

    }
}