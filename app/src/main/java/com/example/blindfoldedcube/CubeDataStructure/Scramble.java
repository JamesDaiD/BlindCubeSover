package com.example.blindfoldedcube.CubeDataStructure;
import android.util.Log;

//TODO: translate scramble into facelets
public class Scramble {
    private static final String TAG = "SCRAMBLE Java";

    public static String scrambleToFacelets(String scramble)
    {
        String solved = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";

//        FaceCube aCube = new FaceCube();
        String[] moves = scramble.split(" ");
        for (int i = 0; i < moves.length; i++)
        {
            Log.d(TAG, moves[i]);
        }
        return scramble + "";
    }
}
