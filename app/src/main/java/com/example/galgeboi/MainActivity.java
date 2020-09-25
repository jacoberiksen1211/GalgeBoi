package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static HistoryLogic history = new HistoryLogic();
    Button btnPlay, btnHistory;
    EditText inputName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup playbtn
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnHistory = findViewById(R.id.mainBtnHistory);
        btnHistory.setOnClickListener(this);

        //get element inputName
        inputName = findViewById(R.id.inputName);

        //add some content to the history list for show
        history.addGame(new GameObj("jacob", "ballade", 4,true));
        history.addGame(new GameObj("Kaj", "computer", 6,false));
        history.addGame(new GameObj("Sofie", "hippie", 4,true));
        history.addGame(new GameObj("Mads", "damer", 6,false));
        history.addGame(new GameObj("Martin", "ballade", 1,true));
        history.addGame(new GameObj("jacob", "ballade", 4,true));
        history.addGame(new GameObj("Kaj", "computer", 6,false));
        history.addGame(new GameObj("Sofie", "hippie", 4,true));
        history.addGame(new GameObj("Mads", "damer", 6,false));
        history.addGame(new GameObj("Martin", "ballade", 1,true));

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