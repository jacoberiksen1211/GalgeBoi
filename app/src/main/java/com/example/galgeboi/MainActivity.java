package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Executor bgThread = Executors.newSingleThreadExecutor();
    Handler uiThread = new Handler();
    Button btnPlay, btnHistory, btnModeSwitch;
    EditText inputName;
    TextView modeChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate game history(parse context to method so historyLogic can use input-/outputstream)
        Galgelogik.getInstance().initGameHistory(getApplicationContext());

        //get elements
        btnPlay = findViewById(R.id.btnPlay);
        btnHistory = findViewById(R.id.mainBtnHistory);
        btnModeSwitch = findViewById(R.id.main_changeMode_btn);

        modeChoice = findViewById(R.id.main_modeChoice_txt);
        inputName = findViewById(R.id.inputName);

        // set onclicklisteners
        btnPlay.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnModeSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnPlay){
            if(inputName.getText().toString().equals("")){
                inputName.setError("Indtast navn");
                Toast.makeText(this, "Indtast navn først", Toast.LENGTH_SHORT).show();
                return;
            }

            //initiate word list using choice
            bgThread.execute(() ->{
                try {
                    Galgelogik.getInstance().initWordList(modeChoice.getText().toString());
                    uiThread.post(() -> {
                        //move to game
                        Intent i = new Intent(this,GameAct.class);
                        i.putExtra("name",inputName.getText().toString());
                        startActivity(i);
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });

        }
        else if(v==btnHistory){
            Intent i = new Intent(this, HistoryAct.class);
            startActivity(i);
        }
        else if(v==btnModeSwitch){
            if(modeChoice.getText().toString().equals("OFFLINE")){
                modeChoice.setText("ONLINE");
            }
            else if (modeChoice.getText().toString().equals("ONLINE")) {
                modeChoice.setText("OFFLINE");
            }
        }
    }
}