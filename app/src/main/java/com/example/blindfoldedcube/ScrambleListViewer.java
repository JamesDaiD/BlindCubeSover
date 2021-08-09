package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.blindfoldedcube.CubeDataStructure.Scramble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScrambleListViewer extends AppCompatActivity {

    ListView scrambleList;
    List<Scramble> scrambles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble_list_viewer);
        scrambleList = findViewById(R.id.scrListView);

        scrambles = ScrambleParser();
        ScrambleListAdapter scrambleAdapter = new ScrambleListAdapter(scrambles);
        scrambleList.setAdapter(scrambleAdapter);

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