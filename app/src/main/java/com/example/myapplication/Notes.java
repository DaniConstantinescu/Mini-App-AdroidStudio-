package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Button add = (Button) findViewById(R.id.addNotes);
        Button viewNotes = (Button) findViewById(R.id.viewNotes);
        Button delete = (Button) findViewById(R.id.deleteNotes);
        Button back = (Button) findViewById(R.id.backNotes);
        Button search = findViewById(R.id.searchNotes);


        search.setOnClickListener(view -> {

            Intent intent = new Intent(Notes.this, SearchNotes.class);
            startActivity(intent);

        });

        add.setOnClickListener(view -> {

            AlertDialog.Builder dialog = new AlertDialog.Builder(Notes.this);
            dialog.setTitle("Add Notes");

            final EditText infoEdit = new EditText(Notes.this);


            infoEdit.setInputType(InputType.TYPE_CLASS_TEXT);
            dialog.setView(infoEdit);

            dialog.setPositiveButton("Add", (dialogInterface, i) -> addNote(infoEdit.getText().toString()) );
            dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());

            dialog.show();


        });
        viewNotes.setOnClickListener(view -> displayNotes());
        delete.setOnClickListener(view -> confirmDelete());
        back.setOnClickListener(view -> finish());

    }

    void addNote(String info){

        info += "\n";

        try {

            OutputStream outputStream = openFileOutput("info.txt", Context.MODE_APPEND);
            outputStream.write(info.getBytes());
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    void confirmDelete(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(Notes.this);
        dialog.setTitle("Delete Notes");

        final TextView infoEdit = new TextView(Notes.this);

        infoEdit.setText("Are you sure you want to delete all the notes?");
        dialog.setView(infoEdit);

        dialog.setPositiveButton("Yes", (dialogInterface, i) -> deleteNotes() );
        dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());

        dialog.show();

    }
    void deleteNotes(){

        try {

            OutputStream outputStream = openFileOutput("info.txt", Context.MODE_PRIVATE);
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    void displayNotes(){

        File file = new File(getFilesDir(), "info.txt");
        StringBuilder stringBuilder = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String notes = stringBuilder.toString();

        if ( notes.isEmpty() )
            Toast.makeText(this, "There are no notes", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, ViewNotes.class);
            startActivity(intent);
        }



    }

}