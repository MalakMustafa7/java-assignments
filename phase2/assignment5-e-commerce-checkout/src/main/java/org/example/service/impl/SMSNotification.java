package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.service.NotificationService;
@Slf4j
public class SMSNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        log.info("Sending SMS: {}", message);
    }
}
