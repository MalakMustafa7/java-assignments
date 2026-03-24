package org.example.interfaces;

import org.example.model.NotificationMessage;

import java.time.LocalDateTime;

public interface Schedulable {
    void schedule(NotificationMessage message, LocalDateTime dateTime);
    void processPending();
}
