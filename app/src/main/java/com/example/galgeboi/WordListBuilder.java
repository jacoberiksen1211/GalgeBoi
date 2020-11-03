package com.example.galgeboi;

import java.util.ArrayList;

public abstract class WordListBuilder {
    private ArrayList<String> wordList;

    public void createWordList(){
        //add words to wordlist
    };

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public void setWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
    }
}
