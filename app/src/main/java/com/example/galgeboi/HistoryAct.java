package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryAct extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    TextView nothingBoxTxt;
    Button clearBtn, backBtn;
    GameArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        clearBtn = findViewById(R.id.history_clear_btn);
        backBtn = findViewById(R.id.history_back_btn);
        nothingBoxTxt = findViewById(R.id.history_nothingBox);


        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        //fill gameHistory into listview using custom adapter
        listView = findViewById(R.id.listView);
        adapter = new GameArrayAdapter(this, R.layout.game_adapter_layout, Galgelogik.getInstance().getHistoryLogic().getGameList());
        listView.setAdapter(adapter);

        isListEmpty();
    }

    //method to check if list is empty and show status box if empty
    public void isListEmpty(){
        if(adapter.isEmpty()){
            nothingBoxTxt.setVisibility(View.VISIBLE);
        }
        else{
            nothingBoxTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == clearBtn){
            Galgelogik.getInstance().getHistoryLogic().clearHistory();
            adapter.notifyDataSetChanged();
            isListEmpty();
        }
        else if(v == backBtn){
            finish();
        }
    }
}