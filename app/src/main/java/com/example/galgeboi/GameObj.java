package com.example.galgeboi;

import java.util.Date;

public class GameObj {
    private String playerName, word;
    private int wrongGuessCount;
    private Date date;



    private boolean gameWon;

    public GameObj(String playerName, String word, int wrongGuessCount, boolean gameWon) {
        this.playerName = playerName;
        this.word = word;
        this.wrongGuessCount = wrongGuessCount;
        this.date = new Date();
        this.gameWon = gameWon;
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

    public Date getDate() {
        return date;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
