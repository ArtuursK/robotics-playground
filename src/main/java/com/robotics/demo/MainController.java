package com.robotics.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String getGame(HttpServletRequest request) {
        //idejas modifikācijām:
        // vairāku spēlētāju lokālā tīklā
        //  - futbols
        //  - tautas bumba

        return "game";
    }

    @RequestMapping(value = "/robot", method = RequestMethod.GET)
    public String getRobot(HttpServletRequest request) {
        return "robot";
    }

}
