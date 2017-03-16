package com.aliasapps.ref.ScoreKeep;

/**
 * Created by mike on 2017-03-14.
 */

public class Event {

    private long id;
    private String description;
    private String time;
    private int type;

    private int homeTeam;
    private int player;

    public Event(){}

    public Event(int type, String time, String description){
        this.type = type;
        this.time = time;
        this.description = description;
    }

    public Event(long id, int type, String time, String description){
        this.id = id;
        this.type = type;
        this.time = time;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(int homeTeam) {
        this.homeTeam = homeTeam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
