package com.example.demo;

public class RobotCommand {
    private String action; // e.g., "forward", "backward", "left", "right"
    private int duration; // duration in miliseconds
    // getters and setters


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
