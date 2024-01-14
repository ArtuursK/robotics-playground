package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FactoryGameController {

    //TODO: this controller will contain endpoints that can send events (SSE) to page
    // to control robots and other aspects of game

    //SSE working principle:
    //factory page "subscribes" to certain emiter endpoint (located in controller - here)
    //whenever some endpoint receives data - parse data and "publish" it (commands) to the emiter which will be
    // picked up by JS in the page and update the scene.

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getGame(HttpServletRequest request) {
        return "factory";
    }

}
