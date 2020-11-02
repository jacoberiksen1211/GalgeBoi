package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay, btnHistory;
    EditText inputName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate game (parse context to method so historyLogic can use input-/outputstream)
        Galgelogik.getInstance().initGame(getApplicationContext());

        //setup playbtn
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnHistory = findViewById(R.id.mainBtnHistory);
        btnHistory.setOnClickListener(this);

        //get element inputName
        inputName = findViewById(R.id.inputName);

    }

    @Override
    public void onClick(View v) {
        if(v==btnPlay){
            Intent i = new Intent(this,GameAct.class);
            i.putExtra("name",inputName.getText().toString());
            startActivity(i);
        }
        else if(v==btnHistory){
            Intent i = new Intent(this, HistoryAct.class);
            startActivity(i);
        }
    }
}