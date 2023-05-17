package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        Button back = (Button) findViewById(R.id.backView);
        back.setOnClickListener(view -> finish());

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        Adapter adapter = new Adapter(this, getNotes());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    List<String> getNotes(){

        File file = new File(getFilesDir(), "info.txt");
        List<String> notes = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                notes.add(line);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notes;

    }

}