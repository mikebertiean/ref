package com.aliasapps.ref.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aliasapps.ref.Games.Game;
import com.aliasapps.ref.ScoreKeep.Event;

import java.util.ArrayList;

/**
 * Created by mike on 2017-03-15.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String LOG = "SQLiteOpenHelper";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ref";

    // Table names
    private static final String TABLE_GAMES = "games";
    private static final String TABLE_EVENTS = "events";

    // Games Table Columns names
    private static final String KEY_GAME_ID = "id";
    private static final String KEY_GAME_HALF_LENGTH = "half_length";
    private static final String KEY_GAME_HOME_COLOR = "home_color";
    private static final String KEY_GAME_AWAY_COLOR = "away_color";
    private static final String KEY_GAME_HOME_GOALS = "home_goals";
    private static final String KEY_GAME_AWAY_GOALS = "away_goals";

    // Events Table Columns names
    private static final String KEY_EVENT_ID = "id";
    private static final String KEY_EVENT_GAME_ID_FK = "game_id";
    private static final String KEY_EVENT_TYPE = "type";
    private static final String KEY_EVENT_HOME_TEAM = "home_team";
    private static final String KEY_EVENT_DESCRIPTION = "description";
    private static final String KEY_EVENT_TIME = "time";
    private static final String KEY_EVENT_PLAYER = "player";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e(LOG, "on create database handler");
        String CREATE_GAMES_TABLE =
                "CREATE TABLE " + TABLE_GAMES
                    + "("
                    + KEY_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_GAME_HALF_LENGTH + " INTEGER,"
                    + KEY_GAME_HOME_COLOR + " TEXT,"
                    + KEY_GAME_AWAY_COLOR + " TEXT,"
                    + KEY_GAME_HOME_GOALS + " INTEGER,"
                    + KEY_GAME_AWAY_GOALS + " INTEGER"
                    + ")";

        String CREATE_EVENTS_TABLE =
                "CREATE TABLE " + TABLE_EVENTS
                    + "("
                    + KEY_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_EVENT_HOME_TEAM + " INTEGER,"
                    + KEY_EVENT_TYPE + " INTEGER,"
                    + KEY_EVENT_PLAYER + " INTEGER,"
                    + KEY_EVENT_DESCRIPTION + " TEXT,"
                    + KEY_EVENT_TIME + " TEXT,"
                    + KEY_EVENT_GAME_ID_FK + " INTEGER, " +
                    "FOREIGN KEY (" + KEY_EVENT_GAME_ID_FK +") REFERENCES " + TABLE_GAMES + " (" + KEY_GAME_ID +")"
                    + ")";

        sqLiteDatabase.execSQL(CREATE_GAMES_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public long addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GAME_HALF_LENGTH, game.getHalfLength());
        values.put(KEY_GAME_HOME_COLOR, game.getHomeColor());
        values.put(KEY_GAME_AWAY_COLOR, game.getAwayColor());
        values.put(KEY_GAME_HOME_GOALS, game.getHomeGoals());
        values.put(KEY_GAME_AWAY_GOALS, game.getAwayGoals());

        // Inserting Row
        long id = db.insert(TABLE_GAMES, null, values);
        db.close(); // Closing database connection

        return id;
    }

    public long updateGame(Game game) {
        System.out.println(game.getHomeGoals());
        
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GAME_HALF_LENGTH, game.getHalfLength());
        values.put(KEY_GAME_HOME_COLOR, game.getHomeColor());
        values.put(KEY_GAME_AWAY_COLOR, game.getAwayColor());
        values.put(KEY_GAME_HOME_GOALS, game.getHomeGoals());
        values.put(KEY_GAME_AWAY_GOALS, game.getAwayGoals());

        // Inserting Row
        long id = db.update(TABLE_GAMES, values, "id=" + game.getId(), null);
        db.close(); // Closing database connection

        return id;
    }

    public Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAMES,
                new String[] {
                        KEY_GAME_ID,
                        KEY_GAME_HALF_LENGTH,
                        KEY_GAME_HOME_COLOR,
                        KEY_GAME_AWAY_COLOR
                },
                KEY_GAME_ID + "=?",
                new String[] {
                        String.valueOf(id)
                }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Game(
                cursor.getInt(cursor.getColumnIndex(KEY_GAME_ID)),
                cursor.getInt(cursor.getColumnIndex(KEY_GAME_HALF_LENGTH)),
                cursor.getString(cursor.getColumnIndex(KEY_GAME_HOME_COLOR)),
                cursor.getString(cursor.getColumnIndex(KEY_GAME_AWAY_COLOR)),
                cursor.getInt(cursor.getColumnIndex(KEY_GAME_HOME_GOALS)),
                cursor.getInt(cursor.getColumnIndex(KEY_GAME_AWAY_GOALS)));
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GAMES;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Game game = new Game(
                        cursor.getInt(cursor.getColumnIndex(KEY_GAME_ID)),
                        cursor.getInt(cursor.getColumnIndex(KEY_GAME_HALF_LENGTH)),
                        cursor.getString(cursor.getColumnIndex(KEY_GAME_HOME_COLOR)),
                        cursor.getString(cursor.getColumnIndex(KEY_GAME_AWAY_COLOR)),
                        cursor.getInt(cursor.getColumnIndex(KEY_GAME_HOME_GOALS)),
                        cursor.getInt(cursor.getColumnIndex(KEY_GAME_AWAY_GOALS)));
                games.add(game);
            } while (cursor.moveToNext());
        }

        // return contact list
        return games;
    }

    public void addEvent(Game game, Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_GAME_ID_FK, game.getId());
        values.put(KEY_EVENT_DESCRIPTION, event.getDescription());
        values.put(KEY_EVENT_PLAYER, event.getPlayer());
        values.put(KEY_EVENT_TIME, event.getTime());
        values.put(KEY_EVENT_HOME_TEAM, event.getHomeTeam());
        values.put(KEY_EVENT_TYPE, event.getType());

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Event> getAllEventsForGame(long gameId) {
        ArrayList<Event> events = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE " + KEY_EVENT_GAME_ID_FK + " = " + gameId;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(
                        cursor.getInt(cursor.getColumnIndex(KEY_EVENT_ID)),
                        cursor.getInt(cursor.getColumnIndex(KEY_EVENT_TYPE)),
                        cursor.getString(cursor.getColumnIndex(KEY_EVENT_TIME)),
                        cursor.getString(cursor.getColumnIndex(KEY_EVENT_DESCRIPTION)));

                event.setHomeTeam(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_HOME_TEAM)));
                event.setPlayer(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_PLAYER)));

                events.add(event);
            } while (cursor.moveToNext());
        }

        // return contact list
        return events;
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_EVENT_ID + " = ?",
                new String[] { String.valueOf(event.getId()) });
        db.close();
    }
}
