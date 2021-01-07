package com.example.galgeboi;

import android.content.Context;
import java.util.ArrayList;
import java.util.Random;

public class Galgelogik {
  private HistoryLogic historyLogic;
  private ArrayList<String> wordList = new ArrayList<String>();
  private String fullWord;
  private ArrayList<String> usedLetters = new ArrayList<String>();
  private String visibleWord;
  private int wrongLetterCount;
  private boolean lastGuessCorrect;
  private boolean gameWon;
  private boolean gameLost;

  public ArrayList<String> getWordList() {
    return wordList;
  }

  public boolean isManualWordChoice() {
    return manualWordChoice;
  }

  public void setManualWordChoice(boolean manualWordChoice) {
    this.manualWordChoice = manualWordChoice;
  }

  private boolean manualWordChoice;

  //setup singleton
  private static Galgelogik instance = null;
  //singleton has private constructor
  private Galgelogik(){
  }
  //getInstance to access singleton
  public static Galgelogik getInstance(){
    if(instance == null){
      instance = new Galgelogik();
    }
    return instance;
  }

  //Initiates history, adds words to wordlist and resets game state
  public void initGameHistory(Context context){
    historyLogic = new HistoryLogic(context);
  }

  public void initWordList(String type){
    wordList.clear();
    WordListBuilderFactory wlFactory = new WordListBuilderFactory();
    WordListBuilder wlBuilder = wlFactory.createWordListBuilder(type);
    wordList = wlBuilder.getWordList();
  }

  public ArrayList<String> getUsedLetters() {
    return usedLetters;
  }

  public String getVisibleWord() {
    return visibleWord;
  }

  public String getFullWord() {
    return fullWord;
  }

  public int getWrongLetterCount() {
    return wrongLetterCount;
  }

  public boolean isLastGuessCorrect() {
    return lastGuessCorrect;
  }

  public boolean isGameWon() {
    return gameWon;
  }

  public boolean isGameLost() {
    return gameLost;
  }

  public HistoryLogic getHistoryLogic(){
    return historyLogic;
  }

  public void resetGame() {
    usedLetters.clear();
    wrongLetterCount = 0;
    gameWon = false;
    gameLost = false;
    if (wordList.isEmpty()) throw new IllegalStateException("Listen over ord er tom!");
    //select random word from word list if manual choice is OFF
    if(manualWordChoice==false){
      fullWord = wordList.get(new Random().nextInt(wordList.size()));
    }
    updateVisibleWord();
  }

  public void setFullWord(String fullWord){
    this.fullWord = fullWord;
  }

  private void updateVisibleWord() {
    visibleWord = "";
    gameWon = true;
    for (int n = 0; n < fullWord.length(); n++) {
      String bogstav = fullWord.substring(n, n + 1);
      if (usedLetters.contains(bogstav)) {
        visibleWord = visibleWord + bogstav;
      } else {
        visibleWord = visibleWord + "*";
        gameWon = false;
      }
    }
  }

  public void guessLetter(String letter) {
    if (letter.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + letter);
    if (usedLetters.contains(letter)) return;
    if (gameWon || gameLost) return;

    usedLetters.add(letter);

    if (fullWord.contains(letter)) {
      lastGuessCorrect = true;
      System.out.println("Bogstavet var korrekt: " + letter);
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      lastGuessCorrect = false;
      System.out.println("Bogstavet var IKKE korrekt: " + letter);
      wrongLetterCount = wrongLetterCount + 1;
      if (wrongLetterCount >= 6) { //har rettet til " >= 6 " i stedet for " > 6 ", da der kun er grafik til 6 fejl IKKE 7. man taber efter 6 fejl så.
        gameLost = true;
      }
    }
    updateVisibleWord();
  }

}
