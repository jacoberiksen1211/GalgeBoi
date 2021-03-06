package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class GameAct extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GameAct";
    Button btnGuess, btnEnd;
    TextView txtWord, txtStatus, txtUsed;
    EditText inputLetter;
    ImageView imgGalge;
    String playerName;
    int imgInts[] = new int[7];

    //mediaplayers for right/wrong sounds
    MediaPlayer rightsound, wrongsound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /*//if there was a manual choice of word before start
        if(Galgelogik.getInstance().isManualWordChoice()){
            Log.d(TAG, "onCreate: manual choice on");
            String chosenword = getIntent().getStringExtra("word");
            Galgelogik.getInstance().setFullWord(chosenword);
        }*/
        //reset game (if manual word ON then no random word selection )
        Galgelogik.getInstance().resetGame();
        //getting elements
        btnGuess = findViewById(R.id.gameBtnGuess);
        btnEnd = findViewById(R.id.gameBtnEnd);
        txtWord = findViewById(R.id.gameTxtWord);
        txtStatus = findViewById(R.id.gameTxtStatus);
        txtUsed = findViewById(R.id.gameTxtUsed);
        inputLetter = findViewById(R.id.inputLetter);
        imgGalge = findViewById(R.id.imgGalge);

        //button onclicklisteners
        btnGuess.setOnClickListener(this);
        btnEnd.setOnClickListener(this);

        //setup name, welcome status and word (get name from intent)
        playerName = getIntent().getStringExtra("name");
        txtStatus.setText("Velkommen til det nye spil, " + playerName);
        txtWord.setText(Galgelogik.getInstance().getVisibleWord());


        //setup image drawable integers from r file for easier image-switching in updateUI()
        imgInts[0] = R.drawable.forkert0;
        imgInts[1] = R.drawable.forkert1;
        imgInts[2] = R.drawable.forkert2;
        imgInts[3] = R.drawable.forkert3;
        imgInts[4] = R.drawable.forkert4;
        imgInts[5] = R.drawable.forkert5;
        imgInts[6] = R.drawable.forkert6;

        //create sound players for right / wrong guess
        rightsound = MediaPlayer.create(this, R.raw.rightsound);
        wrongsound = MediaPlayer.create(this, R.raw.wrongsound);
    }

    @Override
    public void onClick(View v) {
        if(v==btnGuess){
            String letter = inputLetter.getText().toString();
            if(letter.length() != 1){
                txtStatus.setText("Indtast kun ét bogstav.");
                return;
            }
            Galgelogik.getInstance().guessLetter(letter);
            inputLetter.setText("");//fjerner indtastet bogstav.

            updateUI();
        }
        else if(v==btnEnd){
            // from https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            //give up
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setMessage("Giv op?").setPositiveButton("Ja", dialogClickListener)
                    .setNegativeButton("Nej", dialogClickListener).show();

        }
    }

    private void updateUI(){
        //hvis spillet er vundet
        if(Galgelogik.getInstance().isGameWon()){
            //adding game to history
            Galgelogik.getInstance().getHistoryLogic().addGame(new historyGameObj(playerName,Galgelogik.getInstance().getFullWord(),Galgelogik.getInstance().getWrongLetterCount(), true, "freshDate"));
            //create intent and add name to data transfer
            Intent i = new Intent(this, WinnerAct.class);
            i.putExtra("name", playerName);
            //moving to winner act
            startActivity(i);
            finish();
        }
        //hvis spillet er tabt
        else if(Galgelogik.getInstance().isGameLost()){
            //Add new gameobject to history with the given information
            Galgelogik.getInstance().getHistoryLogic().addGame(new historyGameObj(playerName,Galgelogik.getInstance().getFullWord(),Galgelogik.getInstance().getWrongLetterCount(), false, "freshDate"));
            //create intent and add name to data transfer
            Intent i = new Intent(this, LoserAct.class);
            i.putExtra("name", playerName);
            //moving to loser act
            startActivity(i);
            finish();
        }
        //hvis spillet fortsætter:

        //opdater ord
        txtWord.setText(Galgelogik.getInstance().getVisibleWord());

        //opdater brugte bogstaver:
        if(!Galgelogik.getInstance().getUsedLetters().isEmpty()) {
            String usedLetterString = "Brugte bogstaver: \n";
            for (String usedLetter : Galgelogik.getInstance().getUsedLetters()) {
                usedLetterString += usedLetter + " ";
            }
            txtUsed.setText(usedLetterString);
        }
        //opdater billede
        imgGalge.setImageResource(imgInts[Galgelogik.getInstance().getWrongLetterCount()]);

        //opdater status tekst
        if(Galgelogik.getInstance().isLastGuessCorrect()){
            txtStatus.setText("Du gættede rigtigt!");
            rightsound.start();
        }
        else{
            txtStatus.setText("Du gættede forkert. Du har " + (6 - Galgelogik.getInstance().getWrongLetterCount()) + " forsøg igen før GalgeBoi bliver hængt.");
            wrongsound.start();
        }

    }
}