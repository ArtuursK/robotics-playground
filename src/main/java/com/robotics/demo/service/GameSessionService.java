package com.robotics.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSessionService {

    private final List<UUID> players = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;
    private static final int MAX_PLAYERS = 2;

    @Autowired
    public GameSessionService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public synchronized UUID addPlayer() {
        if (players.size() < MAX_PLAYERS) {
            UUID playerId = UUID.randomUUID();
            players.add(playerId);
            return playerId;
        }
        return null; // Session is full
    }

    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    public int getPlayerCount() {
        return players.size();
    }
}
