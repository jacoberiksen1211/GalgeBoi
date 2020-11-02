package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoserAct extends AppCompatActivity implements View.OnClickListener {
    private TextView txtTitle, txtStatus;
    private Button btnRetry, btnEnd, btnHistory;
    private String name, word;
    private int wrongLetterCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser);
        //get elements
        btnRetry = findViewById(R.id.loserBtnRetry);
        btnEnd = findViewById(R.id.loserBtnEnd);
        txtTitle = findViewById(R.id.loserTxtTitle);
        txtStatus = findViewById(R.id.loserTxtStatus);
        btnHistory = findViewById(R.id.loserBtnHistory);
        //set onclicklisteners
        btnRetry.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        //get values for text
        name = getIntent().getStringExtra("name");
        word = Galgelogik.getInstance().getFullWord();
        wrongLetterCount = Galgelogik.getInstance().getWrongLetterCount();
        //set text
        txtTitle.setText("Desværre " + name + "\nDU HAR TABT");
        txtStatus.setText("Ordet var \"" + word + "\"\nDu gættede forkert " + wrongLetterCount + " gange og GalgeBoi blev hængt");
    }

    @Override
    public void onClick(View v) {
        if(v== btnRetry){
            Intent i = new Intent(this, GameAct.class);
            i.putExtra("name", name);
            startActivity(i);
            finish();
        }
        else if(v== btnEnd){
            finish();
        }
        else if(v==btnHistory){
            Intent i = new Intent(this, HistoryAct.class);
            startActivity(i);
        }
    }
}