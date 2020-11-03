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
    WordListFactory wlFactory = new WordListFactory();
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
    fullWord = wordList.get(new Random().nextInt(wordList.size()));
    updateVisibleWord();
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




  /*
   * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
   * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
   * @param sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
   * @throws Exception
   */

  /*public void hentOrdFraRegneark(String sværhedsgrader) throws Exception {
    String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";

    System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/"+id+"/edit?usp=sharing");

    String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
    int linjeNr = 0;

    wordList.clear();
    for (String linje : data.split("\n")) {
      if (linjeNr<20) System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
      if (linjeNr++ < 1 ) continue; // Spring første linje med kolonnenavnene over
      String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
      String sværhedsgrad = felter[0].trim();
      String ordet = felter[1].trim().toLowerCase();
      if (sværhedsgrad.isEmpty() || ordet.isEmpty()) continue; // spring over linjer med tomme ord
      if (!sværhedsgrader.contains(sværhedsgrad)) continue; // filtrér på sværhedsgrader
      System.out.println("Tilføjer "+ordet+", der har sværhedsgrad "+sværhedsgrad);
      wordList.add(ordet);
    }

    System.out.println("muligeOrd = " + wordList);
    resetGame();
  }*/
}
