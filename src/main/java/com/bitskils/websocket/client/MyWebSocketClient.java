package com.bitskils.websocket.client;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyWebSocketClient extends TextWebSocketHandler {

    private WebSocketSession session;

    public void connect() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        String serverUrl = "ws://localhost:8080/websocket"; 
        
        try {
            session = webSocketClient.doHandshake(this, serverUrl).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedMessage = message.getPayload();
        log.info("Received message: " + receivedMessage);
    }
}
