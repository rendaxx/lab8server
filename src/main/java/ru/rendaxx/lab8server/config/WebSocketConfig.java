package ru.rendaxx.lab8server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.rendaxx.lab8server.Updater;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Bean("sessions")
    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/update");

    }

    @Bean
    public WebSocketHandler myHandler() {
        return new Updater(sessions);
    }

}

