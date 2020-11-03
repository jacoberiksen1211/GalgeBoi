package com.example.galgeboi;

public class WordListBuilderFactory {
    public WordListBuilder createWordListBuilder(String type){
        if(type.equals("OFFLINE")){
            return new OfflineWordListBuilder();
        }
        else if(type.equals("ONLINE")){
            return new OnlineWordListBuilder();
        }
        else {
            return null;
        }
    }
}
