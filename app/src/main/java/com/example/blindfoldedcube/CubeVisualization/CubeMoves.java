package com.example.blindfoldedcube.CubeVisualization;

public class CubeMoves {

    //Position of stickers in string
    public static final int
            U1 = 0, U2 = 1, U3 = 2, U4 = 3, U5 = 4, U6 = 5, U7 = 6, U8 = 7, U9 = 8,
            R1 = 9, R2 = 10, R3 = 11, R4 = 12, R5 = 13, R6 = 14, R7 = 15, R8 = 16, R9 = 17,
            F1 = 18, F2 = 19, F3 = 20, F4 = 21, F5 = 22, F6 = 23, F7 = 24, F8 = 25, F9 = 26,
            D1 = 27, D2 = 28, D3 = 29, D4 = 30, D5 = 31, D6 = 32, D7 = 33, D8 = 34, D9 = 35,
            L1 = 36, L2 = 37, L3 = 38, L4 = 39, L5 = 40, L6 = 41, L7 = 42, L8 = 43, L9 = 44,
            B1 = 45, B2 = 46, B3 = 47, B4 = 48, B5 = 49, B6 = 50, B7 = 51, B8 = 52, B9 = 53;

    //Do an UTurn on CubeString
    public static String UTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, U1, U3, U9, U7);
        stickers = SwapCharsInArray(stickers, U2, U6, U8, U4);

        //change side edges
        stickers = SwapCharsInArray(stickers, F2, L2, B2, R2);

        //change side corners
        stickers = SwapCharsInArray(stickers, F1, L1, B1, R1);
        stickers = SwapCharsInArray(stickers, F3, L3, B3, R3);
        return new String(stickers);
    }

    public static String UPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, U1, U3, U9, U7);
        stickers = SwapCharsInArrayReverse(stickers, U2, U6, U8, U4);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, F2, L2, B2, R2);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, F1, L1, B1, R1);
        stickers = SwapCharsInArrayReverse(stickers, F3, L3, B3, R3);
        return new String(stickers);
    }

    public static String DTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, D1, D3, D9, D7);
        stickers = SwapCharsInArray(stickers, D2, D6, D8, D4);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, F8, L8, B8, R8);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, F7, L7, B7, R7);
        stickers = SwapCharsInArrayReverse(stickers, F9, L9, B9, R9);
        return new String(stickers);
    }

    public static String DPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, D1, D3, D9, D7);
        stickers = SwapCharsInArrayReverse(stickers, D2, D6, D8, D4);

        //change side edges
        stickers = SwapCharsInArray(stickers, F8, L8, B8, R8);

        //change side corners
        stickers = SwapCharsInArray(stickers, F7, L7, B7, R7);
        stickers = SwapCharsInArray(stickers, F9, L9, B9, R9);
        return new String(stickers);
    }

    public static String RTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, R1, R3, R9, R7);
        stickers = SwapCharsInArray(stickers, R2, R6, R8, R4);

        //change side edges
        stickers = SwapCharsInArray(stickers, F6, U6, B4, D6);

        //change side corners
        stickers = SwapCharsInArray(stickers, F3, U3, B7, D3);
        stickers = SwapCharsInArray(stickers, F9, U9, B1, D9);
        return new String(stickers);
    }

    public static String RPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, R1, R3, R9, R7);
        stickers = SwapCharsInArrayReverse(stickers, R2, R6, R8, R4);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, F6, U6, B4, D6);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, F3, U3, B7, D3);
        stickers = SwapCharsInArrayReverse(stickers, F9, U9, B1, D9);
        return new String(stickers);
    }

    public static String LPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, L3, L1, L7, L9);
        stickers = SwapCharsInArray(stickers, L2, L4, L8, L6);

        //change side edges
        stickers = SwapCharsInArray(stickers, U4, B6, D4, F4);

        //change side corners
        stickers = SwapCharsInArray(stickers, U1, B9, D1, F1);
        stickers = SwapCharsInArray(stickers, U7, B3, D7, F7);
        return new String(stickers);
    }

    public static String LTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, L3, L1, L7, L9);
        stickers = SwapCharsInArrayReverse(stickers, L2, L4, L8, L6);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, U4, B6, D4, F4);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, U1, B9, D1, F1);
        stickers = SwapCharsInArrayReverse(stickers, U7, B3, D7, F7);
        return new String(stickers);
    }

    public static String FTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, F1, F3, F9, F7);
        stickers = SwapCharsInArray(stickers, F2, F6, F8, F4);

        //change side edges
        stickers = SwapCharsInArray(stickers, L6, U8, R4, D2);

        //change side corners
        stickers = SwapCharsInArray(stickers, L3, U9, R7, D1);
        stickers = SwapCharsInArray(stickers, L9, U7, R1, D3);
        return new String(stickers);
    }

    public static String FPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, F1, F3, F9, F7);
        stickers = SwapCharsInArrayReverse(stickers, F2, F6, F8, F4);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, L6, U8, R4, D2);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, L3, U9, R7, D1);
        stickers = SwapCharsInArrayReverse(stickers, L9, U7, R1, D3);
        return new String(stickers);
    }

    public static String BPrimeTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArrayReverse(stickers, B1, B3, B9, B7);
        stickers = SwapCharsInArrayReverse(stickers, B2, B6, B8, B4);

        //change side edges
        stickers = SwapCharsInArray(stickers, L4, U2, R6, D8);

        //change side corners
        stickers = SwapCharsInArray(stickers, L1, U3, R9, D7);
        stickers = SwapCharsInArray(stickers, L7, U1, R3, D9);
        return new String(stickers);
    }

    public static String BTurn(String cubeString)
    {
        char[] stickers = cubeString.toCharArray();
        //change top face
        stickers = SwapCharsInArray(stickers, B1, B3, B9, B7);
        stickers = SwapCharsInArray(stickers, B2, B6, B8, B4);

        //change side edges
        stickers = SwapCharsInArrayReverse(stickers, L4, U2, R6, D8);

        //change side corners
        stickers = SwapCharsInArrayReverse(stickers, L1, U3, R9, D7);
        stickers = SwapCharsInArrayReverse(stickers, L7, U1, R3, D9);
        return new String(stickers);
    }


    //Swapping algorithms
    public static char[] SwapCharsInArray(char[] charArray, int pos4, int pos3, int pos2, int pos1)
    {
        char temp = charArray[pos1];
        charArray[pos1] = charArray[pos2];
        charArray[pos2] = charArray[pos3];
        charArray[pos3] = charArray[pos4];
        charArray[pos4] = temp;
        return charArray;
    }

    public static char[] SwapCharsInArrayReverse(char[] charArray, int pos1, int pos2, int pos3, int pos4)
    {
        char temp = charArray[pos1];
        charArray[pos1] = charArray[pos2];
        charArray[pos2] = charArray[pos3];
        charArray[pos3] = charArray[pos4];
        charArray[pos4] = temp;
        return charArray;
    }
}
