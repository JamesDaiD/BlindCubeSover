package com.example.blindfoldedcube.IOOperations;

import com.example.blindfoldedcube.MainActivity;
import com.example.blindfoldedcube.R;
import androidx.appcompat.app.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Moved to CubeEdit
//TODO: make globally available
public class ReadCSV {
//    public static List<String[]> readScramblesFromCSV()
//    {
//        List<String[]> dataList = new ArrayList<>();
//
//        //populate the list
//        InputStream inputStream = getResource(R.raw.scrambles);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        try
//        {
//            String csvLine;
//            while ((csvLine = reader.readLine()) != null)
//            {
//                String[] dataInfo = csvLine.split(","); //breakdown line into array
//                dataList.add(dataInfo); //add data to list
//            }
//        }
//        catch (IOException ex)
//        {
//            throw new RuntimeException("Error reading CSV File " + ex.getMessage());
//        }
//        finally {
//            try {
//                inputStream.close();
//            }
//            catch (IOException ex)
//            {
//                throw new RuntimeException("Error closing CSV File " + ex.getMessage());
//            }
//        }
//        return dataList;
//    }
}
