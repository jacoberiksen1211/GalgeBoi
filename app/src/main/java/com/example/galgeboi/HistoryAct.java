package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class HistoryAct extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //fill gameHistory into listview using custom adapter
        listView = findViewById(R.id.listView);
        GameArrayAdapter adapter = new GameArrayAdapter(this, R.layout.adapter_layout, Galgelogik.getInstance().getGameHistory());
        listView.setAdapter(adapter);
    }
}