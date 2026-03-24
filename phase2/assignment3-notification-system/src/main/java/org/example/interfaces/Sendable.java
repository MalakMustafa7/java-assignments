package org.example.interfaces;

import org.example.model.NotificationMessage;

public interface Sendable {
    void send(NotificationMessage message);
}
