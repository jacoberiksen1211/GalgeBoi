package com.example.galgeboi;

public class WordListFactory {
    public WordListBuilder createWordListBuilder(String type){
        if(type.equals("OFFLINE")){
            return new OfflineWordListBuilder();
        }
        else if(type.equals("ONLINE")){
            return new OnlineWordListBuilder();
        }
        else {
            System.out.println("WORDBUILDER STRING FINDES IKKE");
            return null;
        }
    }
}
