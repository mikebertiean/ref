package com.aliasapps.ref.Games;

import java.io.Serializable;

/**
 * Created by mike on 2017-03-15.
 */

public class Game implements Serializable{

    private long id;

    private int halfLength;

    private String homeColor;

    private String awayColor;

    private int homeGoals;

    private int awayGoals;

    public Game(){}

    public Game(int halfLength, String homeColor, String awayColor, int homeGoals, int awayGoals){
        this.halfLength = halfLength;
        this.homeColor = homeColor;
        this.awayColor = awayColor;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public Game(long id, int halfLength, String homeColor, String awayColor, int homeGoals, int awayGoals){
        this.id = id;
        this.halfLength = halfLength;
        this.homeColor = homeColor;
        this.awayColor = awayColor;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public int getHalfLength() {
        return halfLength;
    }

    public void setHalfLength(int halfLength) {
        this.halfLength = halfLength;
    }

    public String getHomeColor() {
        return homeColor;
    }

    public void setHomeColor(String homeColor) {
        this.homeColor = homeColor;
    }

    public String getAwayColor() {
        return awayColor;
    }

    public void setAwayColor(String awayColor) {
        this.awayColor = awayColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }
}
