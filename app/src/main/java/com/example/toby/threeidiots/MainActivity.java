package com.example.toby.threeidiots;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = " ";
    private Button tobyBtn, kyleBtn, bernardBtn, editBtn, newFileBtn;
    private TextView tvToby,tvKyle, tvBernard;
    int scoresTemp[] = {0, 0, 0};
    int scoresFinal[] = {0, 0, 0};
    int LENGTH = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        showDimensions();
        readData("temp_scores.txt");
        readData("winner.txt");
        addScores();
        writeData();
        updateScores();
        clearFile("temp_scores.txt");

    }
    public void showDimensions()
    {
        Display display = getWindowManager().getDefaultDisplay();
        String displayName = display.getName();  // minSdkVersion=17+
        Log.i(TAG, "displayName  = " + displayName);

// display size in pixels
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.i(TAG, "width        = " + width);
        Log.i(TAG, "height       = " + height);

// pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        int densityDpi = metrics.densityDpi;
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        Log.i(TAG, "widthPixels  = " + widthPixels);
        Log.i(TAG, "heightPixels = " + heightPixels);
        Log.i(TAG, "densityDpi   = " + densityDpi);
        Log.i(TAG, "xdpi         = " + xdpi);
        Log.i(TAG, "ydpi         = " + ydpi);

// deprecated
        int screenHeight = display.getHeight();
        int screenWidth = display.getWidth();
        Log.i(TAG, "screenHeight = " + screenHeight);
        Log.i(TAG, "screenWidth  = " + screenWidth);

// orientation (either ORIENTATION_LANDSCAPE, ORIENTATION_PORTRAIT)
        int orientation = getResources().getConfiguration().orientation;
        Log.i(TAG, "orientation  = " + orientation);
    }

    private void init()
    {
        tobyBtn = (Button) findViewById(R.id.button4);
        bernardBtn = (Button) findViewById(R.id.button3);
        kyleBtn = (Button) findViewById(R.id.button5);
        editBtn = (Button) findViewById(R.id.button2);
        newFileBtn = (Button) findViewById(R.id.button6);
        tvToby = (TextView) findViewById(R.id.textView_T);
        tvKyle = (TextView) findViewById(R.id.textView_K);
        tvBernard = (TextView) findViewById(R.id.textView_B);

        tobyBtn.setOnClickListener(this);
        bernardBtn.setOnClickListener(this);
        kyleBtn.setOnClickListener(this);
        editBtn.setOnClickListener(this);
        newFileBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Intent t = new Intent(this, TobyActivity.class);
        Intent k = new Intent(this, KyleActivity.class);
        Intent b = new Intent(this, BernardActivity.class);
        Intent e = new Intent(this, EditActivity.class);
        Intent m = new Intent(this, MainActivity.class);
        switch(view.getId()) {
            case R.id.button4: // toby
                finish();
                startActivity(t);
                break;
            case R.id.button5: // kyle
                finish();
                startActivity(k);
                break;
            case R.id.button3: // bernard
                finish();
                startActivity(b);
                break;
            case R.id.button2: // edit
                finish();
                startActivity(e);
                break;
            case R.id.button6: // clear file
                finish();
                clearFile("winner.txt");
                startActivity(m);
                break;
        }
    }

    public void readData(String str) { // reads data from text file specified by argument
        File file = getApplication().getFileStreamPath(str);
        String lineFromFile;
        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(str)));

                while ((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(lineFromFile);
                    if(str.equals("temp_scores.txt")) {
                        for (int i = 0; i < LENGTH; i++) {
                            str = st.nextToken();
                            scoresTemp[i] = Integer.parseInt(str);
                        }
                    }
                    else if(str.equals("winner.txt")){
                        for (int i = 0; i < LENGTH; i++) {
                            str = st.nextToken();
                            scoresFinal[i] = Integer.parseInt(str);
                        }
                    }
                }
                reader.close();
            }
            catch(IOException e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addScores()
    {
        for(int i = 0; i < LENGTH; i++ ){
            scoresFinal[i] += scoresTemp[i];
        }
    }

    public void writeData()
    {
        try {
            FileOutputStream file = openFileOutput("winner.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for(int i = 0; i < LENGTH; i++) {
                outputFile.write(scoresFinal[i]+ "");
                outputFile.write(" ");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void clearFile(String str){
        try {
            FileOutputStream file = openFileOutput(str, MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for(int i = 0; i < LENGTH; i++)
            {
                outputFile.write("0");
                outputFile.write(" ");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        try {
            FileOutputStream file = openFileOutput(str, MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for(int i = 0; i < LENGTH; i++)
            {
                outputFile.write("0");
                outputFile.write(" ");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void updateScores(){
        tvToby.setText(scoresFinal[0] + ""); // index 0 is toby
        tvBernard.setText(scoresFinal[1] + ""); // index 1 is bernard
        tvKyle.setText(scoresFinal[2] + ""); // index 2 is kyle
    }
}

