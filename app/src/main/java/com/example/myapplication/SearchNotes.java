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

public class SearchNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_notes);

        Button back = (Button) findViewById(R.id.bcc);
        back.setOnClickListener(view -> finish());

        String cuv = "";

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recc);
        Adapter adapter = new Adapter(this, getNotes(cuv));
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }


    List<String> getNotes(String cuv){

        File file = new File(getFilesDir(), "info.txt");
        List<String> notes = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                if ( line.contains(cuv) )
                    notes.add(line);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return notes;

    }

}