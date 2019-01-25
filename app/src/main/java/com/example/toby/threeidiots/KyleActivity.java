package com.example.toby.threeidiots;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class KyleActivity extends AppCompatActivity implements View.OnClickListener{

    Integer players[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    public void init() {
        Button toby = (Button) findViewById(R.id.button14);
        Button bernard = (Button) findViewById(R.id.button12);
        Button kyle = (Button) findViewById(R.id.btnLose);

        toby.setOnClickListener(this);
        bernard.setOnClickListener(this);
        kyle.setOnClickListener(this);

        players = new Integer[3];
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId()){
            case R.id.button14:
                winPlayer("T");
                writeData();
                break;
            case R.id.button12:
                winPlayer("B");
                writeData();
                break;
            case R.id.btnLose:
                winPlayer("K");
                writeData();
                break;
        }
    }
    public void winPlayer(String str)
    {
        switch(str){
            case "K":
                players[2] = 2;
                players[1] = -1;
                players[0] = -1;
                break;
            case "B":
                players[2] = -2;
                players[1] = 1;
                players[0] = 1;
                break;
            case "T":
                players[2] = -2;
                players[1] = 1;
                players[0] = 1;
                break;
        }
    }
    public void writeData()
    {
        try {
            FileOutputStream file = openFileOutput("temp_scores.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for(int i = 0; i < 3; i++)
            {
                outputFile.write(players[i] + "");
                outputFile.write(" ");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch(IOException e)
        {
            Toast.makeText(KyleActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Intent c = new Intent(this, MainActivity.class);
        startActivity(c);
    }

}
