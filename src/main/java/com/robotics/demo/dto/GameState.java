package com.robotics.demo.dto;

import java.util.List;

public class GameState {

    private List<PlayerData> players;

    public GameState() {
    }

    public GameState(List<PlayerData> players) {
        this.players = players;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }
}

