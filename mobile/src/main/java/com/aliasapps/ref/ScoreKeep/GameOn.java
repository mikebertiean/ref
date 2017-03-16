package com.aliasapps.ref.ScoreKeep;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.aliasapps.ref.Games.Game;
import com.aliasapps.ref.R;
import com.aliasapps.ref.SQL.DatabaseHandler;

import java.util.ArrayList;

public class GameOn extends AppCompatActivity {

    ArrayList<Event> eventList;
    EventAdapter eventAdapter;
    Game game;
    TextView time, homeGoals, awayGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_on);

        game = (Game) getIntent().getSerializableExtra("game");

        ListView eventListView = (ListView) findViewById(R.id.events_listview);
        DatabaseHandler db = new DatabaseHandler(this);
        eventList = db.getAllEventsForGame(game.getId());
        db.close();

        eventAdapter = new EventAdapter(eventList);
        eventListView.setAdapter(eventAdapter);

        time = (TextView) findViewById(R.id.time_textview);
        time.setText("00:00");

        homeGoals = (TextView) findViewById(R.id.home_goals_tv);
        awayGoals = (TextView) findViewById(R.id.away_goals_tv);
        homeGoals.setText(game.getHomeGoals()+"");
        awayGoals.setText(game.getAwayGoals()+"");
    }

    private void saveRecord(final Event event){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Choose team & Player")
                .setTitle("Event Details");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DatabaseHandler db = new DatabaseHandler(getApplication());
                db.addEvent(game, event);
                db.close();

                eventList.add(event);
                eventAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void yellowCard(View view) {
        Event event = new Event(
                EventENUM.YELLOW,
                time.getText().toString(),
                "Yellow card");

        saveRecord(event);
        checkYellows(event);
    }

    private void checkYellows(Event event) {
        //check if a player has got 2 yellows and show a red
    }

    public void redCard(View view) {
        saveRecord(new Event(
                EventENUM.RED,
                time.getText().toString(),
                "Red card"));
    }

    public void pauseTime(View view) {

    }

    public void halfTime(View view) {
        time.setText(game.getHalfLength()+"");

        saveRecord(new Event(
                EventENUM.HALF,
                time.getText().toString(),
                "HALF TIME"));
    }

    public void goal(View view) {
        Event event = new Event(
                EventENUM.GOAL,
                time.getText().toString(),
                "GOAL!");
        saveRecord(event);
        addGoal(event);
    }

    private void addGoal(Event event) {
        if(event.getHomeTeam() == 1) game.setHomeGoals(game.getHomeGoals()+1);
        else game.setAwayGoals(game.getAwayGoals()+1);

        DatabaseHandler db = new DatabaseHandler(this);
        db.updateGame(game);
        db.close();

        homeGoals.setText(game.getHomeGoals()+"");
        awayGoals.setText(game.getAwayGoals()+"");
    }
}
