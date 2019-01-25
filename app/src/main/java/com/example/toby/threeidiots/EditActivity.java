//redundant buttons

package com.example.toby.threeidiots;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int LENGTH = 3;
    EditText etToby, etKyle, etBernard;
    Button btnToby, btnKyle, btnBernard, btnMenu; // btn9
    int data[] = {0, 0, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        readData();
        updateText();

    }
    public void init()
    {
        etToby = (EditText)findViewById(R.id.etToby);
        etKyle = (EditText) findViewById(R.id.etKyle);
        etBernard = (EditText) findViewById(R.id.etBernard);

        btnToby = (Button)findViewById(R.id.tBtn);
        btnKyle = (Button)findViewById(R.id.kBtn);
        btnBernard = (Button)findViewById(R.id.bBtn);
        btnMenu = (Button)findViewById(R.id.button9);


        etToby.setOnClickListener(this);
        etBernard.setOnClickListener(this);
        etKyle.setOnClickListener(this);

        btnToby.setOnClickListener(this);
        btnBernard.setOnClickListener(this);
        btnKyle.setOnClickListener(this);
        btnMenu.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent c = new Intent(this, EditActivity.class);
        Intent m = new Intent(this, MainActivity.class);
        switch(view.getId())
        {
            case R.id.tBtn:
                updateData("T");
                writeData();
                break;
            case R.id.kBtn:
                updateData("K");
                writeData();
                break;
            case R.id.bBtn:
                updateData("B");
                writeData();
                break;
            case R.id.button9:
                updateData("B");
                writeData();
                updateData("K");
                writeData();
                updateData("T");
                writeData();
                finish();

                startActivity(m);
                break;

        }
    }
    public void updateData(String str)
    {
        switch(str)
        {
            case "T":
                data[0] = Integer.parseInt(etToby.getText().toString());
                break;
            case "B":
                data[1] = Integer.parseInt(etBernard.getText().toString());
                break;
            case "K":
                data[2] = Integer.parseInt(etKyle.getText().toString());
                break;

        }
    }
    public void writeData()
    {
        try {
            FileOutputStream file = openFileOutput("winner.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for(int i = 0; i < 3; i++)
            {
                outputFile.write(data[i] + "");
                outputFile.write(" ");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException e)
        {
            Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void updateText()
    {
        etToby.setText("" + data[0]);
        etBernard.setText("" + data[1]);
        etKyle.setText("" +data[2]);

    }
    public void readData() {
        String str = "winner.txt";
        File file = getApplication().getFileStreamPath(str);
        String lineFromFile;
        if(file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(str)));

                while ((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(lineFromFile);
                    for (int i = 0; i < LENGTH; i++) {
                        str = st.nextToken();
                        data[i] = Integer.parseInt(str);
                    }
                }
                reader.close();
            }
            catch(IOException e){
                Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }/*
    public void calculateScores()
    {
        String str = readData();
        for(int i = 0; i < str.length(); i+=2) {
            if (str.charAt(i) == 'T') {
                if (str.charAt(i+1) == '+') {
                    tobyScore += str.charAt(i+2);
                }
                if(str.charAt(i) == '-'){
                    i++;
                    tobyScore -= str.charAt(i+2);
                }
            }
            if (str.charAt(i) == 'K') {
                i++;
                if (str.charAt(i) == '+') {
                    i++;
                    kyleScore += str.charAt(i);
                }
                if(str.charAt(i) == '-'){
                    i++;
                    kyleScore -= str.charAt(i);
                }
            }
            if (str.charAt(i) == 'B') {
                i++;
                if (str.charAt(i) == '+') {
                    i++;
                    bernardScore += str.charAt(i);
                }
                if(str.charAt(i) == '-'){
                    i++;
                    bernardScore -= str.charAt(i);
                }
            }
        }

        etToby.setText("" + tobyScore);
        etBernard.setText("" + bernardScore);
        etKyle.setText("" + kyleScore);
    }*/

}
