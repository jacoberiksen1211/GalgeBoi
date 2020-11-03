package com.example.galgeboi;

import java.util.ArrayList;

public class OfflineWordListBuilder extends WordListBuilder {

    public OfflineWordListBuilder(){
        //initiate arraylist
        setWordList(new ArrayList<>());
        //add words to the list
        createWordList();
    }
    @Override
    public void createWordList(){
        getWordList().add("glas");
        /*getWordList().add("bil");
        getWordList().add("computer");
        getWordList().add("programmering");
        getWordList().add("motorvej");
        getWordList().add("busrute");
        getWordList().add("gangsti");
        getWordList().add("skovsnegl");
        getWordList().add("solsort");
        getWordList().add("nitten");
        getWordList().add("telefon");
        getWordList().add("kaffe");
        getWordList().add("kniv");
        getWordList().add("æbleskive");
        getWordList().add("toilet");
        getWordList().add("skolebænk");*/
    }
}
