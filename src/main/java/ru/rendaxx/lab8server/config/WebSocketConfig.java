package ru.rendaxx.lab8server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.rendaxx.lab8server.controllers.UpdateWSController;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private UpdateWSController updateController;

    @Autowired
    public WebSocketConfig(UpdateWSController updateController) {
        this.updateController = updateController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(updateController, "/update");
    }

}

