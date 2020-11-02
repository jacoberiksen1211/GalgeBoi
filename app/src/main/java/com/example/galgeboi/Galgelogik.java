package com.example.galgeboi;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Galgelogik {
  private HistoryLogic history;
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
  public void initGame(Context context){
    history = new HistoryLogic(context);
    wordList.add("bil");
    wordList.add("computer");
    wordList.add("programmering");
    wordList.add("motorvej");
    wordList.add("busrute");
    wordList.add("gangsti");
    wordList.add("skovsnegl");
    wordList.add("solsort");
    wordList.add("nitten");
    wordList.add("telefon");
    wordList.add("kaffe");
    wordList.add("kniv");
    wordList.add("æbleskive");
    wordList.add("toilet");
    wordList.add("skolebænk");
    resetGame();
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

  public ArrayList<historyGameObj> getGameHistory(){
    return history.getGameList();
  }
  public void addGameToHistory(historyGameObj game){
    history.addGame(game);
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

  public static String hentUrl(String url) throws IOException {
    System.out.println("Henter data fra " + url);
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String linje = br.readLine();
    while (linje != null) {
      sb.append(linje + "\n");
      linje = br.readLine();
    }
    return sb.toString();
  }


  /**
   * Hent ord fra DRs forside (https://dr.dk)
   */
  public void hentOrdFraDr() throws Exception {
    String data = hentUrl("https://dr.dk");
    //System.out.println("data = " + data);

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("&#198;", "æ"). // erstat HTML-tegn
            replaceAll("&#230;", "æ"). // erstat HTML-tegn
            replaceAll("&#216;", "ø"). // erstat HTML-tegn
            replaceAll("&#248;", "ø"). // erstat HTML-tegn
            replaceAll("&oslash;", "ø"). // erstat HTML-tegn
            replaceAll("&#229;", "å"). // erstat HTML-tegn
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    System.out.println("data = " + Arrays.asList(data.split("\\s+")));
    wordList.clear();
    wordList.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("muligeOrd = " + wordList);
    resetGame();
  }


  /**
   * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
   * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
   * @param sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
   * @throws Exception
   */

  public void hentOrdFraRegneark(String sværhedsgrader) throws Exception {
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
  }
}
