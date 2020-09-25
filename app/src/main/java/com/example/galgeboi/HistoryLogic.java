package com.example.galgeboi;

import java.util.ArrayList;

public class HistoryLogic {
    private ArrayList<GameObj> gameList;

    public HistoryLogic(){
        gameList = new ArrayList<>();
    }

    public void addGame (GameObj game){
        gameList.add(0,game);
    }
    public ArrayList<GameObj> getGameList(){
        return gameList;
    }
}
