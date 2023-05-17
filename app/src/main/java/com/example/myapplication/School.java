package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class School extends AppCompatActivity {

    EditText nrNumere;
    TextView medie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        nrNumere = (EditText) findViewById(R.id.nrNote);
        medie = (TextView) findViewById(R.id.medieSchool);

        Button ok = (Button) findViewById(R.id.okSchool);
        Button back = (Button) findViewById(R.id.backSchool);

        ok.setOnClickListener(view -> {

            AlertDialog.Builder dialog = new AlertDialog.Builder(School.this);
            dialog.setTitle("The grades with a space between them");

            final EditText nrString = new EditText(School.this);

            if ( nrNumere.getText().toString().equals("") )
                Toast.makeText(School.this, "Complete the number of grades", Toast.LENGTH_SHORT).show();
            else {

                nrString.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog.setView(nrString);

                dialog.setPositiveButton("OK", (dialogInterface, i) -> {

                    /*
                    numere = nrString.getText().toString();
                    System.out.println(numere);

                     */


                    calculateAvg(nrString.getText().toString());

                });

                dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());

                dialog.show();
            }

        });

        back.setOnClickListener(view -> finish());

    }

    void calculateAvg(String strNumere){



        Integer n = Integer.parseInt(nrNumere.getText().toString());

        String[] numere = strNumere.split(" ");

        if (numere.length != n ) {
            Toast.makeText(School.this, "The number of grades is not equal with the one indicated", Toast.LENGTH_SHORT).show();
            medie.setText("");
            return;
        }

        List<Integer> nrInt = new ArrayList<>();

        for (String s : numere)
            nrInt.add(Integer.parseInt(s));

        OptionalDouble average = nrInt
                .stream()
                .mapToDouble(a -> a)
                .average();

        medie.setText("The average is: " + Math.round(average.getAsDouble()*100.0)/100.0);

    }

}