package com.example.galgeboi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/*
 * Class that helps with creating the objects in the list in historyactivity.
 * Custom adapter
 */

public class GameArrayAdapter extends ArrayAdapter<historyGameObj> {
    private Context context;
    private int recourse;

    public GameArrayAdapter(Context context, int resource, ArrayList<historyGameObj> objects) {
        super(context, resource, objects);
        this.context = context;
        this.recourse = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getPlayerName();
        String date = getItem(position).getDate();
        String word = getItem(position).getWord();
        int wrongGuessCount = getItem(position).getWrongGuessCount();
        boolean gameWonBool = getItem(position).isGameWon();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(recourse, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvWord = convertView.findViewById(R.id.tvWord);
        TextView tvFail = convertView.findViewById(R.id.tvFail);
        TextView tvResult = convertView.findViewById(R.id.tvResult);

        tvName.setText(name);
        tvDate.setText(date);
        tvWord.setText("Ord: " + word);
        tvFail.setText("Fejl: " + wrongGuessCount);
        if(gameWonBool){
            tvResult.setText("Vundet");
        }
        else{
            tvResult.setText("Tabt");
        }

        return convertView;

    }
}
