package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class DBSelection extends AppCompatActivity {

    Button scramblesDB;
    Button solvesDB;
    Button algsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbselection);

        scramblesDB = findViewById(R.id.scrambleDBBtn);
        solvesDB = findViewById(R.id.solvesDBBtn);
        algsDB = findViewById(R.id.algsDBBtn);

        scramblesDB.setOnClickListener( view -> {
            startActivity(new Intent(DBSelection.this, ScrambleListViewer.class));
        });

        solvesDB.setOnClickListener( view -> {
            startActivity(new Intent(DBSelection.this, SolveDBViewer.class));
        });
        
        algsDB.setOnClickListener( view -> {
            Toast.makeText(DBSelection.this, "Coming soon", Toast.LENGTH_SHORT).show();
        });
    }
}