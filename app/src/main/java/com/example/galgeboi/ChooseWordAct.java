package com.example.galgeboi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ChooseWordAct extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> wordArray = new ArrayList<>();
    private WordRecyclerAdapter wordRecyclerAdapter;
    private RecyclerView wordRecyclerView;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
        //get UI elements
        wordRecyclerView = findViewById(R.id.chooseword_recyclerView);
        btnBack = findViewById(R.id.chooseword_back_btn);
        //onclick listener for back button
        btnBack.setOnClickListener(this);
        //get list of words from logic
        wordArray = Galgelogik.getInstance().getWordList();

        //create adapter (parsing this context and list of words
        wordRecyclerAdapter = new WordRecyclerAdapter(this, wordArray);
        //set adapter of recyclerview
        wordRecyclerView.setAdapter(wordRecyclerAdapter);
        wordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if(v==btnBack){
            //back button, kill activity
            finish();
        }
    }
}