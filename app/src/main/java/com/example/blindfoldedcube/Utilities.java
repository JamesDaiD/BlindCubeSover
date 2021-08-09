package com.example.blindfoldedcube;

import android.util.Log;

import com.example.blindfoldedcube.CubeVisualization.CubeMoves;

//Utilities functions
public class Utilities {
    //prepare string to be displayed in gridView
    //the char '*' is used to represent blank space on the grid view
    /*
    Original input:
    U1, U2, U3, U4, U5, U6, U7, U8, U9, R1, R2,
    R3, R4, R5, R6, R7, R8, R9, F1, F2, F3, F4, F5, F6, F7, F8, F9, D1, D2, D3, D4, D5, D6, D7, D8, D9, L1, L2, L3, L4,
    L5, L6, L7, L8, L9, B1, B2, B3, B4, B5, B6, B7, B8, B9
    Desired output:
    ***UUU******
    ***UUU******
    ***UUU******
    LLLFFFRRRBBB
    LLLFFFRRRBBB
    LLLFFFRRRBBB
    ***DDD******
    ***DDD******
    ***DDD******
     */
    public static String cubeStringToGridDisplay(String faceCube)
    {
        StringBuilder result = new StringBuilder();
        //Move values to correct positions
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(36,39));//L1-3
        result.append(faceCube.substring(18,21));//F1-3
        result.append(faceCube.substring(9,12));//R1-3
        result.append(faceCube.substring(45,48));//B1-3
        result.append(faceCube.substring(39,42));//L4-6
        result.append(faceCube.substring(21,24));//F4-6
        result.append(faceCube.substring(12,15));//R4-6
        result.append(faceCube.substring(48,51));//B4-6
        result.append(faceCube.substring(42,45));//L7-9
        result.append(faceCube.substring(24,27));//F7-9
        result.append(faceCube.substring(15,18));//R7-9
        result.append(faceCube.substring(51,54));//B7-9
        result.append(faceCube.substring(27,36));//D1-9
        //Buffers for blank squares of grid view
        result.insert(54, "******");
        result.insert(51, "*********");
        result.insert(48, "*********");
        result.insert(45, "***");
        result.insert(9, "******");
        result.insert(6, "*********");
        result.insert(3, "*********");
        result.insert(0, "***");
//        Log.d("cubeStringToGridDisplay", result.toString());
        return result.toString();
    }

    //Restore grid display string to original string
    public static String restoreStringFromGrid(String faceCube)
    {
        //remove * chars
        faceCube = faceCube.replace("*", "");
        StringBuilder result = new StringBuilder();
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9
        result.append(faceCube.substring(0,9));//U1-9

        return result.toString();
    }

    //Turn the solution string into TTS-readable format
    public static String prepareStringforTTS2(String solution)
    {
        solution = solution.replace(" ", "... "); //Slow down TTS so the user can follow the moves
        solution = solution.replace("'", " prime"); //Make sure reverse moves are read
        return solution;
    }

    public static String prepareStringforTTS(String solution)
    {
        String ttsSolution = "";
        String[] moves = solution.split(" ");

        //Add "1" to all single moves so they're easier to follow
        //For example, D2 B' U F B2 -> D2 B' U1 F1 B2
        for (String singleMove : moves)
        {
            if (singleMove.length() == 1)
            {
                singleMove+= "1";
            }
            ttsSolution += singleMove+ " ";
        }

        ttsSolution = ttsSolution.replace(" ", "... \n"); //Slow down TTS so the user can follow the moves
        ttsSolution = ttsSolution.replace("'", " prime"); //Make sure reverse moves are read
        Log.d("scramble", ttsSolution);
        return ttsSolution;
    }



    //Turn scrambles into string representation of a faceCube
    public static String scrambleMovesToFaceCube(String scramble) {
        //starts with solved cube
        String result = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";

        String[] movesToMake = movesStringToArray(scramble);

        for (int i = 0; i < movesToMake.length; i++)
        {
            switch (movesToMake[i].toUpperCase())
            {
                case "U":
                    result = CubeMoves.UTurn(result);
                    break;
                case "U'":
                    result = CubeMoves.UPrimeTurn(result);
                    break;
                case "U2":
                    result = CubeMoves.UTurn(result);
                    result = CubeMoves.UTurn(result);
                    break;
                case "D":
                    result = CubeMoves.DTurn(result);
                    break;
                case "D'":
                    result = CubeMoves.DPrimeTurn(result);
                    break;
                case "D2":
                    result = CubeMoves.DTurn(result);
                    result = CubeMoves.DTurn(result);
                    break;
                case "R":
                    result = CubeMoves.RTurn(result);
                    break;
                case "R'":
                    result = CubeMoves.RPrimeTurn(result);
                    break;
                case "R2":
                    result = CubeMoves.RTurn(result);
                    result = CubeMoves.RTurn(result);
                    break;
                case "L":
                    result = CubeMoves.LTurn(result);
                    break;
                case "L'":
                    result = CubeMoves.LPrimeTurn(result);
                    break;
                case "L2":
                    result = CubeMoves.LTurn(result);
                    result = CubeMoves.LTurn(result);
                    break;
                case "F":
                    result = CubeMoves.FTurn(result);
                    break;
                case "F'":
                    result = CubeMoves.FPrimeTurn(result);
                    break;
                case "F2":
                    result = CubeMoves.FTurn(result);
                    result = CubeMoves.FTurn(result);
                    break;
                case "B":
                    result = CubeMoves.BTurn(result);
                    break;
                case "B'":
                    result = CubeMoves.BPrimeTurn(result);
                    break;
                case "B2":
                    result = CubeMoves.BTurn(result);
                    result = CubeMoves.BTurn(result);
                    break;
                default:
                    Log.d("scrambleToFaceCube", "Invalid entry: " + movesToMake[i]);
            }
        }
        return result;
    }

    public static int getLocationInCubeString(int gridLocation)
    {
        if (gridLocation >=3 && gridLocation <= 5)
            return gridLocation - 3;
        else if(gridLocation >= 14 && gridLocation <=17)
            return gridLocation -12;
        else if(gridLocation >= 26 && gridLocation <= 29)
            return gridLocation -21;
        else if(gridLocation >= 36 && gridLocation <=38)
            return gridLocation;
        //TODO: finish conversions
        else
            return 0;
    }

    //Turn string of scramble into an array of strings, each containing one move
    public static String[] movesStringToArray(String scramble)
    {
        //remove brackets from scramble if needed
        scramble = scramble.replace("(", "");
        scramble = scramble.replace(")", "");

        String[] resultArray = scramble.split(" ");
        return resultArray;
    }
}
