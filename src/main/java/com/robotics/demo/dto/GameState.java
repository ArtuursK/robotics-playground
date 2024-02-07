package com.robotics.demo.dto;

import java.util.List;

public class GameState {

    private List<PlayerData> playerDataList;

    public GameState() {
    }

    public GameState(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
    }

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public void setPlayerDataList(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
    }
}

