package com.example.galgeboi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordRecyclerAdapter extends RecyclerView.Adapter<WordRecyclerAdapter.ViewHolder> {


    private static final String TAG = "WordRecyclerAdapter";

    private ArrayList<String> mWords;
    private Context mContext;

    public WordRecyclerAdapter(Context mContext, ArrayList<String> mWords) {
        this.mWords = mWords;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            String word = mWords.get(position);
            holder.word.setText(word);
        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView word;
        public ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word_item_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            //set chosen word in game logic
            Galgelogik.getInstance().setFullWord(mWords.get(getAdapterPosition()));
            //go to game, parse name from intent
            Intent i = new Intent(mContext, GameAct.class);
            i.putExtra("name", ((ChooseWordAct) mContext).getIntent().getStringExtra("name"));
            mContext.startActivity(i);
            ((ChooseWordAct) mContext).finish();
        }
    }

}















