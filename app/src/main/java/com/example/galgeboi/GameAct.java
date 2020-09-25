package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.galgeboi.MainActivity.history;

public class GameAct extends AppCompatActivity implements View.OnClickListener {
    Galgelogik galgelogik = new Galgelogik();
    Button btnGuess, btnEnd;
    TextView txtWord, txtStatus, txtUsed;
    EditText inputLetter;
    ImageView imgGalge;
    String name;
    int imgInts[] = new int[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //getting elements
        btnGuess = findViewById(R.id.gameBtnGuess);
        btnEnd = findViewById(R.id.gameBtnEnd);
        txtWord = findViewById(R.id.gameTxtWord);
        txtStatus = findViewById(R.id.gameTxtStatus);
        txtUsed = findViewById(R.id.gameTxtUsed);
        inputLetter = findViewById(R.id.inputLetter);
        imgGalge = findViewById(R.id.imgGalge);

        //buttonclick
        btnGuess.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

        //setup name and welcome status and word
        name = getIntent().getStringExtra("name");
        txtStatus.setText("Velkommen til det nye spil, " + name);
        txtWord.setText(galgelogik.getSynligtOrd());


        //setup image drawable integers from r file for easier image-switching in updateUI()
        imgInts[0] = R.drawable.forkert0;
        imgInts[1] = R.drawable.forkert1;
        imgInts[2] = R.drawable.forkert2;
        imgInts[3] = R.drawable.forkert3;
        imgInts[4] = R.drawable.forkert4;
        imgInts[5] = R.drawable.forkert5;
        imgInts[6] = R.drawable.forkert6;
    }

    @Override
    public void onClick(View v) {
        if(v==btnGuess){
            String letter = inputLetter.getText().toString();
            if(letter.length() != 1){
                txtStatus.setText("Indtast kun ét bogstav.");
                return;
            }
            galgelogik.gætBogstav(letter);
            inputLetter.setText("");//fjerner indtastet bogstav.

            updateUI();
        }
        else if(v==btnEnd){
            finish();
        }
    }

    private void updateUI(){
        //hvis spillet er done
        if(galgelogik.erSpilletVundet()){
            //adding game to history
            history.addGame(new GameObj(name,galgelogik.getOrdet(),galgelogik.getAntalForkerteBogstaver(), true));
            //moving to winner act
            Intent i = new Intent(this, WinnerAct.class);
            i.putExtra("name", name);
            i.putExtra("word", galgelogik.getOrdet());
            i.putExtra("lives", galgelogik.getAntalForkerteBogstaver());
            startActivity(i);
            finish();
        }
        else if(galgelogik.erSpilletTabt()){
            //add game to history
            history.addGame(new GameObj(name,galgelogik.getOrdet(),galgelogik.getAntalForkerteBogstaver(), false));
            //moving to loser act
            Intent i = new Intent(this, LoserAct.class);
            i.putExtra("name", name);
            i.putExtra("word", galgelogik.getOrdet());
            i.putExtra("lives", galgelogik.getAntalForkerteBogstaver());
            startActivity(i);
            finish();
        }

        //opdater ord
        txtWord.setText(galgelogik.getSynligtOrd());

        //opdater brugte bogstaver:
        if(!galgelogik.getBrugteBogstaver().isEmpty()) {
            String usedLetterString = "Brugte bogstaver: \n";
            for (String usedLetter : galgelogik.getBrugteBogstaver()) {
                usedLetterString += usedLetter + " ";
            }
            txtUsed.setText(usedLetterString);
        }
        //opdater billede
        imgGalge.setImageResource(imgInts[galgelogik.getAntalForkerteBogstaver()]);

        //opdater status
        if(galgelogik.erSidsteBogstavKorrekt()){
            txtStatus.setText("Du gættede rigtigt!");
        }
        else{
            txtStatus.setText("Du gættede forkert. Du har " + (6 - galgelogik.getAntalForkerteBogstaver()) + " forsøg igen før GalgeBoi bliver hængt.");
        }

    }
}