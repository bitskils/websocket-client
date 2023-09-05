package com.bitskils.websocket.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class WebsocketClientApplication {

    @Autowired
    private MyWebSocketClient webSocketClient;

    public static void main(String[] args) {
        SpringApplication.run(WebsocketClientApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        webSocketClient.connect();
        webSocketClient.sendMessage("Hello, WebSocket Server!");
    }
}
