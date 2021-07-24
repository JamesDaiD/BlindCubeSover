package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.blindfoldedcube.MyCubeDatabase.AppDatabase;
import com.example.blindfoldedcube.MyCubeDatabase.SolveEntry;

import java.util.List;

public class DBViewer extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<SolveEntry> solves;

    private SolveEntryAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbviewer);
//        getApplicationContext().deleteDatabase("MyCubeDB");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "MyCubeDB")
                .allowMainThreadQueries() //allow DB to read and write on main UI thread. Not suitable for the real world
                .build();

        solves = db.solveDao().getAllSolves();
        recyclerView = findViewById(R.id.solvesRecView);

        setOnClickListener();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SolveEntryAdapter(solves, listener);
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener()
    {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), SavedSolveView.class);
            intent.putExtra("solveScramble", solves.get(position).getSolveScramble());
            intent.putExtra("solveSolution", solves.get(position).getSolveSolution());
            startActivity(intent);
        };
    }


}