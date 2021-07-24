package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.blindfoldedcube.CubeVisualization.CubeGrid2DAdapter;

public class SavedSolveView extends AppCompatActivity {

    GridView gridViewCube;
    TextView solutionTV;

    String solveScramble = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";
    String solveSolution = "Solution not set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_solve_view);

        solutionTV = findViewById(R.id.solutionHolderTV);

        //Grid view to show Cube 2D GUI
        gridViewCube = findViewById(R.id.cubeGridEdit);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            solveScramble = extras.getString("solveScramble");
            solveSolution = extras.getString("solveSolution");
        }

        solutionTV.setText(solveSolution);
        //Show cube
        gridViewCube.setAdapter(
                new CubeGrid2DAdapter(solveScramble));
        gridViewCube.setHorizontalSpacing(0);
        gridViewCube.setVerticalSpacing(0);
        gridViewCube.setNumColumns(12);
    }
}