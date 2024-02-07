package com.robotics.demo.service;

import com.robotics.demo.dto.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSessionService {

    private final List<PlayerData> players = new ArrayList<>();

    //TODO: private final List<EnvironmentData> environmentData = new ArrayList<>(); used for storing the positions of game cubes
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
            String playerId = UUID.randomUUID().toString(); // Auto-generate the playerID (UUID)
            newPlayer.setPlayerId(playerId);

            // TODO: set default values for position, rotation, and scale here!!! important because these will get updated
            newPlayer.setPosition(Map.of("x", 0.0f, "y", 0.0f, "z", 0.0f));
            newPlayer.setRotation(Map.of("x", 0.0f, "y", 0.0f, "z", 0.0f, "w", 1.0f));
            newPlayer.setScale(Map.of("x", 1.0f, "y", 1.0f, "z", 1.0f));

            players.add(newPlayer); // Assuming 'players' is a List<PlayerData>

            if(players.size() > 1) {
                messagingTemplate.convertAndSend("/topic/new-player", playerId);
            }
        }
        return newPlayer; // Indicate that the session is full
    }

    public void updateGameState(PlayerData playerData) {
        System.out.println("game state is being updated: " + playerData.toString());
        for(PlayerData playerData1 : players) {
            System.out.println(playerData1);
        }

        //find the player id in the list of players and modify coordinates etc.
        players.forEach( pd -> {
            if(pd.getPlayerId().equals(playerData.getPlayerId())){
                pd.setPosition(playerData.getPosition());
                pd.setRotation(playerData.getRotation());
                pd.setScale(playerData.getScale());
                pd.setLastRobotCommand(playerData.getLastRobotCommand());
            }
        });
    }

    /**
     * A scheduled method triggered every ? seconds to broadcast current game state to all players so that they are in sync.
     */
    @Scheduled(fixedRate = 1000)
    public void broadCastCurrentGameStateToAllClients() {
        //System.out.println("_____________ The time is now " + System.currentTimeMillis());
        messagingTemplate.convertAndSend("/topic/state-update", getCurrentGamePlayersState());
    }

    public List<PlayerData> getCurrentGamePlayersState() {
        return players;
    }

    public boolean isFull() {
        return players.size() >= MAX_PLAYERS;
    }

    public int getPlayerCount() {
        return players.size();
    }


}
