package com.robotics.demo.service;

import com.robotics.demo.dto.GameState;
import com.robotics.demo.dto.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSessionService {

    private final List<PlayerData> players = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;
    private static final int MAX_PLAYERS = 3;

    @Autowired
    public GameSessionService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public synchronized PlayerData addPlayer() {
        PlayerData newPlayer = new PlayerData();
        newPlayer.setPlayerId("");
        if (players.size() < MAX_PLAYERS) {
            String playerId = UUID.randomUUID().toString(); // Auto-generate the UUID
            newPlayer.setPlayerId(playerId);

            // You might set default values for position, rotation, and scale here
            newPlayer.setPosition(Map.of("x", 0.0f, "y", 0.0f, "z", 0.0f));
            newPlayer.setRotation(Map.of("x", 0.0f, "y", 0.0f, "z", 0.0f, "w", 1.0f));
            newPlayer.setScale(Map.of("x", 1.0f, "y", 1.0f, "z", 1.0f));

            players.add(newPlayer); // Assuming 'players' is a List<PlayerData>
            // Here you might broadcast the updated game state or the addition of the new player to all clients
            broadcastPlayerJoin(newPlayer);
        }
        return newPlayer; // Indicate that the session is full
    }

    public GameState getCurrentGameState() {
        GameState gameState = new GameState();
        gameState.setPlayers(new ArrayList<>(this.players)); // Create a copy of the current players list
        // Set other game state information as needed
        return gameState;
    }

    private void broadcastPlayerJoin(PlayerData newPlayer) {
        // Assuming you're using SimpMessagingTemplate for WebSocket communication
        messagingTemplate.convertAndSend("/topic/game-update", newPlayer);
    }

    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    public int getPlayerCount() {
        return players.size();
    }
}
