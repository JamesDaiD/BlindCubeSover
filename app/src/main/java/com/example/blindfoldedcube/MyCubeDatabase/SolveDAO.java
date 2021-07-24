package com.example.blindfoldedcube.MyCubeDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SolveDAO {
    @Query("SELECT * FROM SolveEntry")
    List<SolveEntry> getAllSolves();

    @Insert
    void insertAll(SolveEntry...solveEntries);
}
