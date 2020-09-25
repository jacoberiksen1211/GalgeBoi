package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinnerAct extends AppCompatActivity implements View.OnClickListener {
    private TextView txtTitle, txtStatus;
    private Button btnRetry, btnEnd, btnHistory;
    private String name, word;
    private int antalForkerte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        btnRetry = findViewById(R.id.winnerBtnRetry);
        btnEnd = findViewById(R.id.winnerBtnEnd);
        txtTitle = findViewById(R.id.winnerTxtTitle);
        txtStatus = findViewById(R.id.winnerTxtStatus);
        btnHistory = findViewById(R.id.winnerBtnHistory);

        btnRetry.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnHistory.setOnClickListener(this);

        name = getIntent().getStringExtra("name");
        word = getIntent().getStringExtra("word");
        antalForkerte = getIntent().getIntExtra("lives",9999);

        txtTitle.setText("Tillykke " + name + "!\nDU HAR VUNDET!");
        txtStatus.setText("Du gættede ordet \"" + word + "\"\n med kun " + antalForkerte + " forkerte gæt");
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
            finish();
        }
    }
}