package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.galgeboi.MainActivity.history;

public class HistoryAct extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = findViewById(R.id.listView);

        //ArrayList<GameObj> gameList = new ArrayList<>();
        //random games for the list

        /*
        gameList.add(new GameObj("jacob", "ballade", 4,true));
        gameList.add(new GameObj("Kaj", "computer", 6,false));
        gameList.add(new GameObj("Sofie", "hippie", 4,true));
        gameList.add(new GameObj("Mads", "damer", 6,false));
        gameList.add(new GameObj("Martin", "ballade", 1,true));
        gameList.add(new GameObj("jacob", "ballade", 4,true));
        gameList.add(new GameObj("Kaj", "computer", 6,false));
        gameList.add(new GameObj("Sofie", "hippie", 4,true));
        gameList.add(new GameObj("Mads", "damer", 6,false));
        gameList.add(new GameObj("Martin", "ballade", 1,true));
*/
        GameArrayAdapter adapter = new GameArrayAdapter(this, R.layout.adapter_layout, history.getGameList());

        listView.setAdapter(adapter);
    }
}