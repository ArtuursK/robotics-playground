package com.robotics.demo.dto;

import java.util.Map;

public class PlayerData {

    private String playerId;
    private Map<String, Float> position;
    private Map<String, Float> rotation;
    private Map<String, Float> scale;

    // Constructor
    public PlayerData() {
    }

    public PlayerData(String playerId, Map<String, Float> position, Map<String, Float> rotation, Map<String, Float> scale) {
        this.playerId = playerId;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    // Getters and Setters
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Map<String, Float> getPosition() {
        return position;
    }

    public void setPosition(Map<String, Float> position) {
        this.position = position;
    }

    public Map<String, Float> getRotation() {
        return rotation;
    }

    public void setRotation(Map<String, Float> rotation) {
        this.rotation = rotation;
    }

    public Map<String, Float> getScale() {
        return scale;
    }

    public void setScale(Map<String, Float> scale) {
        this.scale = scale;
    }

}
