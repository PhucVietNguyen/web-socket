package com.socket.version.job;

import com.socket.version.config.MyWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class SendMessage {

    private final MyWebSocketHandler myWebSocketHandler;

    @Scheduled(fixedDelay = 1000) // Execute every 1 second
    public void sendScheduledMessage() {
        myWebSocketHandler.sendMessage("test");
    }
}
