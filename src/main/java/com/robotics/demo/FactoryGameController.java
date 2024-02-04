package com.robotics.demo;

import com.robotics.demo.dto.GameState;
import com.robotics.demo.dto.PlayerData;
import com.robotics.demo.dto.RobotCommand;
import com.robotics.demo.service.GameSessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FactoryGameController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private GameSessionService gameSessionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFactoryGameRootPath(HttpServletRequest request) {
        return "factory";
    }

    @RequestMapping(value = "/factory", method = RequestMethod.GET)
    public String getFactoryGameFactoryPath(HttpServletRequest request) {
        return "factory";
    }

    @RequestMapping(value = "/online-factory", method = RequestMethod.GET)
    public String getOnlineFactoryGame(HttpServletRequest request, Model model) {
        //Everyone joins a single session. If no session exists, a new one is created
        String playerId = gameSessionService.addPlayer().getPlayerId();
        System.out.println("Current player count: " + gameSessionService.getPlayerCount());
        if (playerId != null) {
            if(gameSessionService.getPlayerCount() == 1) {
                model.addAttribute("playerId", "origin");
            } else {
                model.addAttribute("playerId", playerId);
                messagingTemplate.convertAndSend("/topic/new-player", playerId);
            }
        } else {
            model.addAttribute("playerId", "");
            // TODO: Handle the case where the session is full
            //  This could redirect to a "session full" page or return a specific message
            //  or add a simple watcher that cannot control any robots
        }
        return "online-factory";
    }

    @PostMapping("/online-factory/robot/move")
    public ResponseEntity<?> moveFactoryRobot(@RequestBody RobotCommand command) {
        //determine who (which team) is sending the command and direct this command to a separate topic (topic with ID)
        messagingTemplate.convertAndSend("/topic/move", command);
        //the command will be picked up by all frontends and the respective robot will be moved
        return ResponseEntity.ok().build();
    }

    @GetMapping("/online-factory/game-state")
    public ResponseEntity<GameState> getCurrentGameState() {
        GameState gameState = gameSessionService.getCurrentGameState();
        return ResponseEntity.ok(gameState);
    }

    @MessageMapping("/new-playessr")
    public void newPlayer(PlayerData playerData) {
        System.out.println("STOMP message - new player - received");
        // Broadcast the new player data to all clients
        messagingTemplate.convertAndSend("/topic/new-player", playerData);
    }




}
