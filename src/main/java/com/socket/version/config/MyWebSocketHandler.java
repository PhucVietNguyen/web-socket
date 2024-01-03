package com.socket.version.config;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MyWebSocketHandler implements WebSocketHandler {

  private WebSocketSession session;

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    System.out.println("Client connected: " + session.getId());
    this.session = session;
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
      throws Exception {
    System.out.println("Received message: " + message.getPayload());
    session.sendMessage(new TextMessage("Hello from server"));
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    System.out.println("Client disconnected: " + session.getId());
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
      throws Exception {
    System.out.println("Error occurred in WebSocket session: " + session.getId());
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }

  @Scheduled(fixedDelay = 1000) // Execute every 5 minutes
  public void sendScheduledMessage() {
    if (session != null && session.isOpen()) {
      String message = "Scheduled message at " + LocalDateTime.now();
      try {
        session.sendMessage(new TextMessage(message));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
