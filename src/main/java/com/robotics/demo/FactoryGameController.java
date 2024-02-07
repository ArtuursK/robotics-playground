package com.robotics.demo;

import com.robotics.demo.dto.PlayerData;
import com.robotics.demo.dto.RobotCommand;
import com.robotics.demo.service.GameSessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
        if (playerId != null && gameSessionService.getPlayerCount() <= 3) {
            if(gameSessionService.getPlayerCount() == 1) {
                model.addAttribute("playerId", "origin");
                System.out.println("Game is ready to be started");
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
        // TODO: return current game state:  model.addAttribute("gameState", gameState);
        return "online-factory";
    }

    @PostMapping("/online-factory/api/move-robot")
    public ResponseEntity<?> moveFactoryRobot(@RequestBody RobotCommand robotCommand) {
        //TODO: get current player state and send that too?

        //determine who (which team) is sending the command and direct this command to a separate topic (topic with ID)
        messagingTemplate.convertAndSend("/topic/move-robot", robotCommand);
        //the above command will be picked up by all frontends and the respective robot will be moved

        return ResponseEntity.ok().build();
    }

    @MessageMapping("/robot-moved") // message receiver
    @SendTo("/topic/move-robot") //broadcast message to topic (other clients)
    public RobotCommand processRobotMovedEvent(PlayerData playerData) {
        System.out.println("Robot " + playerData.getPlayerId() + " is being moved!");
        //Process the move (e.g., update the game state with robot command)
        gameSessionService.updateGameState(playerData);
        return playerData.getRobotCommand();
    }


}
