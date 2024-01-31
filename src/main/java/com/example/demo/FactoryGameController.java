package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FactoryGameController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //TODO: this controller will contain endpoints that can send events (SSE) to page
    // to control robots and other aspects of game

    //SSE working principle:
    //factory page "subscribes" to certain emiter endpoint (located in controller - here)
    //whenever some endpoint receives data - parse data and "publish" it (commands) to the emiter which will be
    // picked up by JS in the page and update the scene.

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getFactoryGameRootPath(HttpServletRequest request) {
        return "factory";
    }

    @RequestMapping(value = "/factory", method = RequestMethod.GET)
    public String getFactoryGameFactoryPath(HttpServletRequest request) {
        return "factory";
    }

    @RequestMapping(value = "/online-factory", method = RequestMethod.GET)
    public String getOnlineFactoryGame(HttpServletRequest request) {
        return "online-factory";
    }

    @PostMapping("/online-factory/robot/move")
    public ResponseEntity<?> moveFactoryRobot(@RequestBody RobotCommand command) {
        messagingTemplate.convertAndSend("/topic/move", command);
        return ResponseEntity.ok().build();
    }


}
