package com.example.blindfoldedcube.MyCubeDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SolveEntry.class}, version = 1) //version is incremented everytime
public abstract class AppDatabase extends RoomDatabase {
    public abstract SolveDAO solveDao();
}
