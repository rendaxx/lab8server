package ru.rendaxx.lab8server;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

import static org.springframework.web.socket.CloseStatus.SERVER_ERROR;

@Log
@RequiredArgsConstructor
public class Updater implements WebSocketHandler {

    private final Map<String, WebSocketSession> sessions;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        var principal = session.getPrincipal();

        if (principal == null || principal.getName() == null) {
              session.close(SERVER_ERROR.withReason("User must be authenticated"));
            return;
        }

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
}

