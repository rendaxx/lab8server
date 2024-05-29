package ru.rendaxx.lab8server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateNotifier {
    List<UpdateListener> listeners;

    @Autowired
    public UpdateNotifier(List<UpdateListener> listeners) {
        this.listeners = listeners;
    }

    public void notifyAction() {
        listeners.forEach(UpdateListener::onUpdate);
    }
}
