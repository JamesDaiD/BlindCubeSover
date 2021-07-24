package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.blindfoldedcube.MyCubeDatabase.AppDatabase;
import com.example.blindfoldedcube.MyCubeDatabase.SolveEntry;

import java.util.List;

public class DBViewer extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbviewer);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "MyCubeDB")
                .allowMainThreadQueries() //allow DB to read and write on main UI thread. Not suitable for the real world
                .build();

        List<SolveEntry> solves = db.solveDao().getAllSolves();
        recyclerView = findViewById(R.id.solvesRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SolveEntryAdapter(solves);
        recyclerView.setAdapter(adapter);
    }
}