package com.example.blindfoldedcube.CubeDataStructure;
import android.util.Log;

//TODO: translate scramble into facelets
public class Scramble {
    private static final String TAG = "SCRAMBLE Java";
    private int scrambleNo;
    private String scramble;

    public Scramble(int scrambleNo, String scramble) {
        this.scrambleNo = scrambleNo;
        this.scramble = scramble;
    }

    public int getScrambleNo() {
        return scrambleNo;
    }

    public void setScrambleNo(int scrambleNo) {
        this.scrambleNo = scrambleNo;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

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
