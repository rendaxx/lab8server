package ru.rendaxx.lab8server.controllers;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import ru.rendaxx.lab8server.service.CollectionServiceImpl;
import ru.rendaxx.lab8server.util.LocalDateTypeAdapter;
import ru.rendaxx.lab8server.util.UpdateListener;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.springframework.web.socket.CloseStatus.SERVER_ERROR;

@Log
@RequiredArgsConstructor
@Component
public class UpdateWSController implements WebSocketHandler, UpdateListener {

    private final Map<String, WebSocketSession> sessions;
    private ApplicationContext applicationContext;

    @Autowired
    public UpdateWSController(Map<String, WebSocketSession> sessions, ApplicationContext applicationContext) {
        this.sessions = sessions;
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        var principal = session.getPrincipal();

        if (principal == null || principal.getName() == null) {
              session.close(SERVER_ERROR.withReason("User must be authenticated"));
            return;
        }

        log.info("User " + principal.getName() + " established WebSocket connection");

        session.sendMessage(new TextMessage(new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create().toJson(applicationContext.getBean(CollectionServiceImpl.class).load())));

        sessions.put(principal.getName(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try {
            session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Messages not supported"));
        } catch (IOException ex) {
            // ignore
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)  {

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        var principal = session.getPrincipal();
        sessions.remove(principal.getName());
        session.close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void onUpdate() {
        var message = new TextMessage(new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create().toJson(applicationContext.getBean(CollectionServiceImpl.class).load()));
        sessions.forEach((key, val) -> {
            try {
                val.sendMessage(message);
            } catch (IOException e) {
                log.warning("Failed to send message to " + key);
            }
        });
    }
}

