package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduledNotification {
    private NotificationMessage message;
   private LocalDateTime scheduledTime;
}
