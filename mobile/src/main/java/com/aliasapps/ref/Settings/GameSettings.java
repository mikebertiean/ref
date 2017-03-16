package com.aliasapps.ref.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.aliasapps.ref.Games.Game;
import com.aliasapps.ref.R;
import com.aliasapps.ref.SQL.DatabaseHandler;
import com.aliasapps.ref.ScoreKeep.GameOn;

public class GameSettings extends AppCompatActivity {

    NumberPicker digit1, digit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        digit1 = (NumberPicker) findViewById(R.id.half_digit1);
        digit2 = (NumberPicker) findViewById(R.id.half_digit2);

        digit1.setValue(4);
        digit1.setMinValue(0);
        digit1.setMaxValue(9);

        digit2.setValue(5);
        digit2.setMinValue(0);
        digit2.setMaxValue(9);
    }

    public void ready(View view) {
        Game game = new Game();

        game.setHalfLength(Integer.parseInt(digit1.getValue()+""+digit2.getValue()));
        game.setHomeColor("000");
        game.setAwayColor("fff");
        game.setHomeGoals(0);
        game.setAwayGoals(0);

        DatabaseHandler db = new DatabaseHandler(this);
        game.setId(db.addGame(game));
        db.close();

        Intent gameOn = new Intent(this, GameOn.class);
        gameOn.putExtra("game", game);
        this.startActivity(gameOn);
    }
}
