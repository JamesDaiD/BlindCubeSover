package com.example.blindfoldedcube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blindfoldedcube.CubeDataStructure.Search;
import com.example.blindfoldedcube.CubeVisualization.CubeGrid2DAdapter;
import com.example.blindfoldedcube.MyCubeDatabase.AppDatabase;
import com.example.blindfoldedcube.MyCubeDatabase.SolveEntry;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Timer;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTTS;
    boolean speaking = false;

    private int seconds = 0;

    private boolean running;
    private boolean wasRunning;

    TextView solutionTV;
    Button solveButton;
    Button saveSolveBtn;
    Button openDBBtn;
    Button ttsBtn;
    Button moveBtn;
    SeekBar ttsSpeedSB;
    GridView gridViewCube;
    ProgressBar progressBar;
//    Chronometer chronometer;

    String currentCubeState = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";
    boolean chronometerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        scrambleTV = findViewById(R.id.scrambleText);
        solutionTV = findViewById(R.id.solutionText);
        moveBtn = findViewById(R.id.editCubeBtn);
        saveSolveBtn = findViewById(R.id.saveSolveBtn);
        openDBBtn = findViewById(R.id.openDBButton);
        ttsBtn = findViewById(R.id.ttsBtn);
        ttsSpeedSB = findViewById(R.id.speedSB);
        solveButton = findViewById(R.id.solveBtn);

        progressBar = findViewById(R.id.progressBar);



        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "MyCubeDB")
                .allowMainThreadQueries() //allow DB to read and write on main UI thread. Not suitable for the real world
                .build();

        loadCubeState();

        //**************************************************************************************//
        //Grid view to show Cube 2D GUI
        gridViewCube = findViewById(R.id.cubeGridEdit);
        //Show cube
        gridViewCube.setAdapter(
                new CubeGrid2DAdapter(currentCubeState));
        gridViewCube.setHorizontalSpacing(0);
        gridViewCube.setVerticalSpacing(0);
        gridViewCube.setNumColumns(12);

        //**************************************************************************************//
        //prepare TTS engine
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        ttsBtn.setText("Text to speech");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        },  "com.google.android.tts"); //attempt to use Google speech engine if available.
                                            // Can use "com.samsung.SMT" for samsung engine


        //Listeners

        //Solve
        solveButton.setOnClickListener(view -> {
            solutionTV.setText("Generating solution... First run can take up to 2 minutes");
            startAsyncTask(currentCubeState);
        });

        //Open DB
        openDBBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SolveDBViewer.class));
        });

        //Save solve into roomDB
        saveSolveBtn.setOnClickListener(view -> {
            String scramble = currentCubeState;
            String solution = solutionTV.getText().toString();

            db.solveDao().insertAll((new SolveEntry(scramble, solution)));
            Toast.makeText(this, "Solve saved to Database", Toast.LENGTH_SHORT).show();
        });
        runTimer();
        //Start TTS
        ttsBtn.setOnClickListener(view -> {

            if (!mTTS.isSpeaking() && !speaking) {
                seconds = 0;
                running = true;
                String textToRead = solutionTV.getText().toString();
                textToRead = Utilities.prepareStringforTTS(textToRead);
                String[] movesArray = textToRead.split("... \n");
                ttsBtn.setText("Reading. Click to stop");
                Thread background = new Thread(() -> {
                    try {
                        speaking = true;
                        for (int i = 0; i < movesArray.length; i++) {
                            mTTS.setPitch(0.9f);
                            float speed = (float) ttsSpeedSB.getProgress() / 100;
                            mTTS.setSpeechRate(speed);
                            mTTS.speak(movesArray[i], TextToSpeech.QUEUE_FLUSH, null, null); //TODO: check other params
                            sleep(2300 - (ttsSpeedSB.getProgress() * 10));
                        }

                        ttsBtn.setText("Text to speech");
                        running = false;
                    } catch (Exception e) {
                    }
                });
            // start thread
            background.start();

//            startChronometer();
            }
            else
            {
                running = false;
                mTTS.stop();
                speaking = false;
//                stopChronometer();
                ttsBtn.setText("Text to speech");
            }
        });

        //open activity to edit cube state
        moveBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CubeEdit.class));
        });
    }

//    class exampleThread extends Thread
//    {
//        @Override
//        public void run() {
//            String test = Tools.randomCube();
//            Search.solution(test, 25, 5L, true);
//        }
//    }
    @Override
    protected void onResume()
    {
        super.onResume();
        updateCubeGrid();
    }

    //Clean up after Text to speech
    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    public void updateCubeGrid()
    {
        loadCubeState();
        //Update cube view to match new random cube
        gridViewCube.setAdapter(new CubeGrid2DAdapter(currentCubeState));
        gridViewCube.invalidateViews();
    }


    //Used for loading the table without hanging the UI
    //TODO: Move to threads
    public void startAsyncTask(String test)
    {
        AsyncTablePrune tablePrune = new AsyncTablePrune(this);
        tablePrune.execute();
    }

    public class AsyncTablePrune extends AsyncTask<String, String, String> //input for doInBg, input for ProgressUpdate, output for doInBg
    {
        private WeakReference<MainActivity> activityWeakReference; //prevents memory leaks
        String cubeToSolve;
        String result;

        AsyncTablePrune(MainActivity activity)
        {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        AsyncTablePrune(MainActivity activity, String cubeToSolve)
        {
            activityWeakReference = new WeakReference<MainActivity>(activity);
            this.cubeToSolve = cubeToSolve;
        }

        @Override
        protected void onPreExecute() {
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
            {
                return;
            }
            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
            {
                return "Something went wrong";
            }
            String cube = currentCubeState;
            Log.d("scrambletosolve", currentCubeState);
            result = Search.solution(cube, 29, 6L, false);
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
            {
                return;
            }
            activity.progressBar.setVisibility(View.INVISIBLE);
            Log.d("scramble", result);
            activity.solutionTV.setText(result);
            saveSolveBtn.setEnabled(true);
            ttsBtn.setEnabled(true);
        }
    }

    public void saveCubeState()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Cube", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("cubeState", currentCubeState);

        editor.apply();
    }

    public void loadCubeState()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Cube", Context.MODE_PRIVATE);
        currentCubeState = sharedPreferences.getString("cubeState", "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB");
    }

    private void runTimer(){
        final TextView timeField = findViewById(R.id.timeField);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/36000;
                int minutes = (seconds % 3600)/60;
                int sec = seconds % 60;

                String time = String.format(Locale.getDefault(), "%d:%02d:%2d", hours, minutes, sec);
                timeField.setText(time);

                if (running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

//    class BackgroundThread extends Thread {
//        @Override
//        public void run() {
//            super.run();
//        }
//    }





}