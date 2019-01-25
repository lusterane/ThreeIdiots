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

public class TobyActivity extends AppCompatActivity implements View.OnClickListener{
    Integer players[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    public void init() {
        Button win = (Button) findViewById(R.id.btnWin);
        Button lose = (Button) findViewById(R.id.btnLose);

        win.setOnClickListener(this);
        lose.setOnClickListener(this);

        players = new Integer[3];
    }
    @Override
    public void onClick(View view)
    {
        switch(view.getId()){
            case R.id.btnWin:
                winPlayer(true);
                writeData();
                break;
            case R.id.btnLose:
                winPlayer(false);
                writeData();
                break;
        }
    }
    public void winPlayer(boolean bool)
    {
        if(bool) {
            players[0] = 2;
            players[1] = -1;
            players[2] = -1;
        }
        else {
            players[0] = -2;
            players[1] = 1;
            players[2] = 1;
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
            Toast.makeText(TobyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Intent c = new Intent(this, MainActivity.class);
        startActivity(c);
    }
}
