package com.sisca.recyclearview;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlayerResult {

    @SerializedName( value = "player", alternate = {"players"})
    private ArrayList<Player> players;

    public PlayerResult(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
