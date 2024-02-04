package com.robotics.demo;

public class RobotCommand {

    private String playerId; // e.g., "072fb3fb-6faf-4f9b-b2cf-2c5d79701f22" given when a player has joined the game
    private String action; // e.g., "forward", "backward", "left", "right"
    private int duration; // duration in seconds

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
