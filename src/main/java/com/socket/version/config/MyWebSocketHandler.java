package com.socket.version.config;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.*;

@Log4j2
public class MyWebSocketHandler implements WebSocketHandler {

    private WebSocketSession webSocketSession;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Socket is connected.");
        log.info("Session ID:" + session.getId());
        this.webSocketSession = session;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
            throws Exception {
        //Todo handle received message if there
        log.warn("Received message: " + message.getPayload());
        log.info("Session ID:" + session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Session ID:" + session.getId());
        log.error("Error:" + exception.getMessage());
        this.webSocketSession = null;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
            throws Exception {
        log.info("Session ID:" + session.getId() + ", status:" + closeStatus.getReason());
        log.warn("Connection is closed.");
        this.webSocketSession = null;
    }

    @Override
    public boolean supportsPartialMessages() {
        return Boolean.FALSE;
    }

    public void sendMessage(Object obj) {
        if (this.webSocketSession != null && this.webSocketSession.isOpen()) {
            Gson gson = new Gson();
            try {
                this.webSocketSession.sendMessage(new TextMessage(gson.toJson(obj)));
            } catch (Exception ex) {
                log.error("Err :" + ex.getMessage());
            }
        }
    }
}
