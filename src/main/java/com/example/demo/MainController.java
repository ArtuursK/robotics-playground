package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String getGame(HttpServletRequest request) {
        return "game";
    }

    @RequestMapping(value = "/factory-game", method = RequestMethod.GET)
    public String getFactoryGame(HttpServletRequest request) {
        return "factory-game";
    }

    @RequestMapping(value = "/robot", method = RequestMethod.GET)
    public String getRobot(HttpServletRequest request) {
        return "robot";
    }

    @RequestMapping(value = "/robot2", method = RequestMethod.GET)
    public String getRobot2(HttpServletRequest request) {
        return "robot2";
    }
}
