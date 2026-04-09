package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.service.NotificationService;
@Slf4j
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        log.info("Sending Email: {}", message);
    }
}
