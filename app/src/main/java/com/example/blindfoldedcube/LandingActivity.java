package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LandingActivity extends AppCompatActivity {

    List<Integer> pageImg = new ArrayList<>(Arrays.asList(R.drawable.rubik,R.drawable.rubik_move,
            R.drawable.server,R.drawable.guidebook));
    List<String> pageName =new ArrayList<>(Arrays.asList("Solve","Edit Cube","Database",
            "Tutorial"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        GridView gridViewImages = findViewById(R.id.landingGridView);

        gridViewImages.setAdapter(new LandingGridAdapter(pageName,pageImg));
        gridViewImages.setHorizontalSpacing(20);
        gridViewImages.setVerticalSpacing(40);
        gridViewImages.setNumColumns(2);

        gridViewImages.setOnItemClickListener((parent, view, position, id) -> {
            switch(position){
                case 0:
                    startActivity(new Intent(LandingActivity.this, MainActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(LandingActivity.this, CubeEdit.class));
                    break;
                case 2:
                    startActivity(new Intent(LandingActivity.this, SolveDBViewer.class));
                    break;
                case 3:
                    startActivity(new Intent(LandingActivity.this, ScrambleListViewer.class));
                    break;
                default:
                    Toast.makeText(LandingActivity.this, "Yo, something is wrong", Toast.LENGTH_SHORT).show();
            }});

    }
    
}