package ru.rendaxx.lab8server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import ru.rendaxx.lab8server.entity.Organization;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Configuration
public class AppConfig {

    @Bean(name="organizationSet")
    public Set<Organization> organizationSet() {
        return new ConcurrentSkipListSet<>();
    }

    @Bean("sessions")
    public Map<String, WebSocketSession> getSessions() {
        return new HashMap<>();
    }
}
