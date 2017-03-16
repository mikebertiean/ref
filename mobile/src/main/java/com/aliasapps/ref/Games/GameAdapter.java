package com.aliasapps.ref.Games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aliasapps.ref.R;
import com.aliasapps.ref.ScoreKeep.GameOn;

import java.util.ArrayList;

/**
 * Created by mike on 2017-03-15.
 */

class GameAdapter extends BaseAdapter {

    ArrayList<Game> games;

    GameAdapter(ArrayList<Game> games) {
        this.games = games;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int i) {
        return games.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Game game = games.get(position);

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    parent.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.game_cell, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameOn = new Intent(parent.getContext(), GameOn.class);
                gameOn.putExtra("game", game);
                parent.getContext().startActivity(gameOn);
            }
        });

        TextView date = (TextView) convertView.findViewById(R.id.game_date);
        date.setText(game.getId()+"");

        System.out.println(game.getId()+"");

        return convertView;
    }
}
