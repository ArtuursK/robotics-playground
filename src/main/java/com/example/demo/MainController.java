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

    @RequestMapping(value = "/factory", method = RequestMethod.GET)
    public String getFactory(HttpServletRequest request) {
        return "factory";
    }

    @RequestMapping(value = "/robot", method = RequestMethod.GET)
    public String getRobot(HttpServletRequest request) {
        return "robot";
    }

}
