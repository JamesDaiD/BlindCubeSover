package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.blindfoldedcube.CubeDataStructure.Scramble;
import com.example.blindfoldedcube.CubeDataStructure.Tools;
import com.example.blindfoldedcube.CubeVisualization.CubeGrid2DAdapter;
import com.example.blindfoldedcube.CubeVisualization.CubeGridAdapterPaintable;
import com.example.blindfoldedcube.CubeVisualization.CubeMoves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    Button redBtn;
    Button orangeBtn;
    Button whiteBtn;
    Button yellowBtn;
    Button blueBtn;
    Button greenBtn;

    char selectedColor = 'R';

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
                new CubeGridAdapterPaintable(currentCubeState));
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

        redBtn = findViewById(R.id.RedBtn);
        orangeBtn = findViewById(R.id.OrangeBtn);
        whiteBtn = findViewById(R.id.WhiteBtn);
        yellowBtn = findViewById(R.id.YellowBtn);
        blueBtn = findViewById(R.id.BlueBtn);
        greenBtn = findViewById(R.id.GreenBtn);

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
            currentCubeState = Utilities.scrambleMovesToFaceCube(scrambleToCubeET.getText().toString());
            updateCubeGrid();
        });

        randomCubeBtn.setOnClickListener(view -> {
            List<Scramble> scramblesList = ScrambleParser();
            Random rand = new Random();
            String randomScrMoves = scramblesList.get(rand.nextInt(scramblesList.size())).getScramble();
//            currentCubeState = Tools.randomCube();
            scrambleToCubeET.setText(randomScrMoves);
            currentCubeState = Utilities.scrambleMovesToFaceCube(scrambleToCubeET.getText().toString());
            updateCubeGrid();
        });

        redBtn.setOnClickListener(view -> {
            selectedColor = 'R';
        });

        orangeBtn.setOnClickListener(view -> {
            selectedColor = 'L';
        });

        whiteBtn.setOnClickListener(view -> {
            selectedColor = 'U';
        });

        yellowBtn.setOnClickListener(view -> {
            selectedColor = 'D';
        });

        greenBtn.setOnClickListener(view -> {
            selectedColor = 'F';
        });

        blueBtn.setOnClickListener(view -> {
            selectedColor = 'B';
//            Log.d("StickerColor", "B");
        });


        gridViewCube.setOnItemClickListener((adapterView, view, i, l) -> {
            TextView clickedSticker = view.findViewById(R.id.textView);
            char stickerState = clickedSticker.getText().toString().charAt(0);
            Log.d("StickerState", "Something " + stickerState + i);
            if (stickerState != ' ' && stickerState != '*')
            {
                clickedSticker.setText(selectedColor + "");
                char[] tempChar = currentCubeState.toCharArray();
                tempChar[Utilities.getLocationInCubeString(i)] = selectedColor;
                currentCubeState = String.valueOf(tempChar);
                switch (selectedColor) {
                    case 'U':
                        clickedSticker.setBackgroundColor(Color.WHITE);
                        break;
                    case 'D':
                        clickedSticker.setBackgroundColor(Color.YELLOW);
                        break;
                    case 'R':
                        clickedSticker.setBackgroundColor(Color.RED);
                        break;
                    case 'L':
                        clickedSticker.setBackgroundColor(Color.rgb(255, 165, 0)); //ORANGE
                        break;
                    case 'F':
                        clickedSticker.setBackgroundColor(Color.GREEN);
                        break;
                    case 'B':
                        clickedSticker.setBackgroundColor(Color.BLUE);
                        break;
                    default:
                        break;
                }
            }
//            updateCubeGrid();
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
        gridViewCube.setAdapter(new CubeGridAdapterPaintable(currentCubeState));
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

    /*get Data from CSV file*/
    private List<String[]> ReadCSV()
    {
        List<String[]> dataList = new ArrayList<>();

        //populate the list
        InputStream inputStream = getResources().openRawResource(R.raw.scrambles);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String csvLine;
            while ((csvLine = reader.readLine()) != null)
            {
                String[] dataInfo = csvLine.split(","); //breakdown line into array
                dataList.add(dataInfo); //add data to list
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException("Error reading CSV File " + ex.getMessage());
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException ex)
            {
                throw new RuntimeException("Error closing CSV File " + ex.getMessage());
            }
        }
        return dataList;
    }

    /*used to parse scramble from DB*/
    public List<Scramble> ScrambleParser()
    {
        List<String[]> dataFromFile = ReadCSV(); //get data from CSV file

        List<Scramble> scrambleList = new ArrayList<>(); //holds scramble objects

        /*loop through data and creates objects*/
        for (int i = 0; i < dataFromFile.size(); i++)
        {

            /*creates scramble object*/
            Scramble aScramble = new Scramble
                    (
                            Integer.parseInt(dataFromFile.get(i)[0]), //scramble no
                            dataFromFile.get(i)[1] //actual scramble
                    );
            scrambleList.add(aScramble); //add object to list
        }
        return scrambleList;
    } //ends scrambleParser
}