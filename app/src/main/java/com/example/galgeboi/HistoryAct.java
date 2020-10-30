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

        //fill gamelist into listview
        listView = findViewById(R.id.listView);
        GameArrayAdapter adapter = new GameArrayAdapter(this, R.layout.adapter_layout, history.getGameList());
        listView.setAdapter(adapter);
    }
}