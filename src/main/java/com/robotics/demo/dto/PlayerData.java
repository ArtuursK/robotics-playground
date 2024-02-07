package com.robotics.demo.dto;

import java.util.Map;

public class PlayerData {

    private String playerId;
    private Map<String, Float> position;
    private Map<String, Float> rotation;
    private Map<String, Float> scale;
    private RobotCommand lastRobotCommand;

    // Constructor
    public PlayerData() {
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

    public RobotCommand getLastRobotCommand() {
        return lastRobotCommand;
    }

    public void setLastRobotCommand(RobotCommand lastRobotCommand) {
        this.lastRobotCommand = lastRobotCommand;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "playerId='" + playerId + '\'' +
                ", position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                ", lastRobotCommand=" + lastRobotCommand +
                '}';
    }
}
