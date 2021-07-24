package com.example.blindfoldedcube.MyCubeDatabase;

import androidx.room.*;

//Entries holding the scrambles and corresponding solutions
@Entity
public class SolveEntry {

    @PrimaryKey(autoGenerate = true) //generate id for each entity
    private int id;
    @ColumnInfo(name = "solve_scramble")
    private String solveScramble;
    @ColumnInfo(name = "solve_solution")
    private String solveSolution;

    public SolveEntry(String solveScramble, String solveSolution) {
        this.solveScramble = solveScramble;
        this.solveSolution = solveSolution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSolveScramble() {
        return solveScramble;
    }

    public void setSolveScramble(String solveScramble) {
        this.solveScramble = solveScramble;
    }

    public String getSolveSolution() {
        return solveSolution;
    }

    public void setSolveSolution(String solveSolution) {
        this.solveSolution = solveSolution;
    }
}
