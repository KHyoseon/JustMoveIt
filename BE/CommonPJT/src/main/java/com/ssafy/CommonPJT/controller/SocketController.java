package com.ssafy.CommonPJT.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SocketController {

    @GetMapping("/socket")
    public String socketGET(){

        log.info("@ChatController, socket GET()");

        return "socket";
    }
}