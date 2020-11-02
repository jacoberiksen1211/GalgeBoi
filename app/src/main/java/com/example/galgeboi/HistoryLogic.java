package com.example.galgeboi;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Class that contains the history of games in a list of GameObjects
 * Handles saving and loading game history from local storage
 * Appends ";"-seperated "txt"-file every time a game is finished
 * Loads the file once when app is started (constructor called from initGame() in Galgelogik)
 */

public class HistoryLogic {
    private static final String FILE_NAME = "history.txt";

    private ArrayList<historyGameObj> gameList;

    private Context context; // application context set when created

    public HistoryLogic(Context context){
        gameList = new ArrayList<>();
        this.context = context;

        //add some games to the list for show
        initHistory();
    }

    public void addGame (historyGameObj game){
        //add game object to gameList at start of list.
        gameList.add(0,game);

        //**append the textfile containing history**
        // create ; seperated string
        String gameString = game.getPlayerName() + ";" +
                game.getWord() + ";" +
                game.getWrongGuessCount() + ";" +
                game.isGameWon() + ";" +
                game.getDate() + "\n";

        // setup outpustream
        FileOutputStream outputStream = null;
        try {
            outputStream =  context.openFileOutput(FILE_NAME, context.MODE_APPEND);
            outputStream. write(gameString.getBytes());
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //gets history from saved file and adds to gamelist
    private void initHistory (){
        FileInputStream inputStream = null;
        String inputTotal = "";
        try {
            //setup read chain
            inputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String input;
            //read each line from file
            while((input = bufferedReader.readLine()) != null){
                stringBuilder.append(input).append("\n");
            }
            //add to "inputTotal)
            inputTotal = stringBuilder.toString();
            streamReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        //if there are any games in history
        if(!inputTotal.equals("")) {
            //split by lineshift so each game is on its own string[] index
            String[] gameStringList = inputTotal.split("\n");
            //for each game split by ";" so each parameter is on own string[] index and add that historyGameObject to gameList
            for (String gameString : gameStringList) {
                String[] gameParam = gameString.split(";");
                gameList.add(0, (new historyGameObj(gameParam[0], gameParam[1], Integer.parseInt(gameParam[2]), Boolean.parseBoolean(gameParam[3]), gameParam[4])));
            }
        }
    }

    public ArrayList<historyGameObj> getGameList(){
        return gameList;
    }
}
