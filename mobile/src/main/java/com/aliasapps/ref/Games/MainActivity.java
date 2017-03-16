package com.aliasapps.ref.Games;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.aliasapps.ref.SQL.DatabaseHandler;
import com.aliasapps.ref.Settings.GameSettings;
import com.aliasapps.ref.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String LOG = "Main Activity";

    ArrayList<Game> gameList;
    GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(LOG, "onCreate");

        ListView gameListView = (ListView) findViewById(R.id.game_listview);
        DatabaseHandler db = new DatabaseHandler(this);
        gameList = db.getAllGames();
        db.close();

        gameAdapter = new GameAdapter(gameList);
        gameListView.setAdapter(gameAdapter);
    }

    public void startSetup(View view) {
        Intent gameSettings = new Intent(this, GameSettings.class);
        this.startActivity(gameSettings);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e(LOG, "onResume");
    }
}
