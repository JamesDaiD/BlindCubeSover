package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.blindfoldedcube.CubeVisualization.CubeGrid2DAdapter;
import com.example.blindfoldedcube.CubeVisualization.CubeMoves;

public class CubeEdit extends AppCompatActivity {

    GridView gridViewCube;
    String currentCubeState = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";

    Button UBtn;
    Button UPrimeBtn;
    Button DBtn;
    Button DPrimeBtn;
    Button RBtn;
    Button RPrimeBtn;
    Button LBtn;
    Button LPrimeBtn;
    Button FBtn;
    Button FPrimeBtn;
    Button BBtn;
    Button BPrimeBtn;

    Button resetCubeBtn;
    Button randomCubeBtn;
    Button applyScrambleBtn;

    EditText scrambleToCubeET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_edit);

        gridViewCube = findViewById(R.id.cubeGridEdit);

        //**************************************************************************************//
        loadCubeState();

        //Grid view to show Cube 2D GUI
        gridViewCube = findViewById(R.id.cubeGridEdit);
        //Show cube
        gridViewCube.setAdapter(
                new CubeGrid2DAdapter(currentCubeState));
        gridViewCube.setHorizontalSpacing(0);
        gridViewCube.setVerticalSpacing(0);
        gridViewCube.setNumColumns(12);

        /**************************************************************************************/
        //Move cube buttons
        UBtn = findViewById(R.id.UBtn);
        UPrimeBtn = findViewById(R.id.UPrimeBtn);
        DBtn = findViewById(R.id.DBtn);
        DPrimeBtn = findViewById(R.id.DPrimeBtn);
        RBtn = findViewById(R.id.RBtn);
        RPrimeBtn = findViewById(R.id.RPrimeBtn);
        LBtn = findViewById(R.id.LBtn);
        LPrimeBtn = findViewById(R.id.LPrimeBtn);
        FBtn = findViewById(R.id.FBtn);
        FPrimeBtn = findViewById(R.id.FPrimeBtn);
        BBtn = findViewById(R.id.BBtn);
        BPrimeBtn = findViewById(R.id.BPrimeBtn);

        resetCubeBtn = findViewById(R.id.resetBtn);
        randomCubeBtn = findViewById(R.id.randomBtn);
        applyScrambleBtn = findViewById(R.id.applyScrambleBtn);

        scrambleToCubeET = findViewById(R.id.scrambleToCubeET);

        UBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.UTurn(currentCubeState);
            updateCubeGrid();
        });

        UPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.UPrimeTurn(currentCubeState);
            updateCubeGrid();
        });

        DBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.DTurn(currentCubeState);
            updateCubeGrid();
        });

        DPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.DPrimeTurn(currentCubeState);
            updateCubeGrid();
        });

        RBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.RTurn(currentCubeState);
            updateCubeGrid();
        });

        RPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.RPrimeTurn(currentCubeState);
            updateCubeGrid();
        });

        LBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.LTurn(currentCubeState);
            updateCubeGrid();
        });

        LPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.LPrimeTurn(currentCubeState);
            updateCubeGrid();
        });

        FBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.FTurn(currentCubeState);
            updateCubeGrid();
        });

        FPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.FPrimeTurn(currentCubeState);
            updateCubeGrid();
        });

        BBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.BTurn(currentCubeState);
            updateCubeGrid();
        });

        BPrimeBtn.setOnClickListener(view -> {
            currentCubeState = CubeMoves.BPrimeTurn(currentCubeState);
            updateCubeGrid();
        });


        resetCubeBtn.setOnClickListener(view -> {
            currentCubeState = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";
            updateCubeGrid();
        });

        applyScrambleBtn.setOnClickListener(view -> {
            currentCubeState = Utilities.scrambleToFaceCube(scrambleToCubeET.getText().toString());
            updateCubeGrid();
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadCubeState();
        updateCubeGrid();
    }

    public void updateCubeGrid()
    {
        Log.d("cubeStateEdit", currentCubeState);
        gridViewCube.setAdapter(new CubeGrid2DAdapter(currentCubeState));
        gridViewCube.invalidateViews();
        saveCubeState();
    }

    public void saveCubeState()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Cube", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("cubeState", currentCubeState);

        editor.apply();
    }

    public void loadCubeState()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Cube", Context.MODE_PRIVATE);
        currentCubeState = sharedPreferences.getString("cubeState", "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB");
    }
}