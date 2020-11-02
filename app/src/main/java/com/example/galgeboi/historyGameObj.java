package com.example.galgeboi;

import java.util.Date;

/*
 * Class that holds information for a single game
 * Used in Arraylist in historylogic
 */

public class historyGameObj {
    private String playerName, word;
    private int wrongGuessCount;
    private String dateString;
    private boolean gameWon;

    public historyGameObj(String playerName, String word, int wrongGuessCount, boolean gameWon, String dateString) {
        this.playerName = playerName;
        this.word = word;
        this.wrongGuessCount = wrongGuessCount;
        this.gameWon = gameWon;
        //if dateString parameter is freshDate, create new date to give current timestamp to object
        if(dateString.equals("freshDate")){
            Date date = new Date();
            this.dateString = date.toString();
        }
        else{
            this.dateString = dateString;
        }
    }
    public String getPlayerName() {
        return playerName;
    }

    public String getWord() {
        return word;
    }

    public int getWrongGuessCount() {
        return wrongGuessCount;
    }

    public String getDate() {
        return dateString;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
