package org.example.notifier;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.DeliveryStatus;
import org.example.interfaces.Schedulable;
import org.example.interfaces.Sendable;
import org.example.interfaces.Notifier;
import org.example.model.NotificationMessage;
import org.example.model.ScheduledNotification;
import org.example.repositoy.NotificationMessageRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
@Getter
@Slf4j
public class PushNotifier implements Sendable, Schedulable, Notifier {
    private final Queue<ScheduledNotification> scheduledQueue = new LinkedList<>();
    private final NotificationMessageRepository repository;

    public PushNotifier(NotificationMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void schedule(NotificationMessage message, LocalDateTime dateTime) {
        scheduledQueue.add(new ScheduledNotification(message, dateTime));
        log.info("Push notification scheduled for: {}", dateTime);
    }

    @Override
    public void processPending() {
        LocalDateTime now = LocalDateTime.now();
        while (!scheduledQueue.isEmpty() && !scheduledQueue.peek().getScheduledTime().isAfter(now)) {
            send(scheduledQueue.poll().getMessage());
        }
    }

    @Override
    public void send(NotificationMessage message) {
        log.info("Sending Push: {}", message.getContent());
        message.setDeliveryStatus(DeliveryStatus.SENT);
        repository.saveMessageStatus(message.getMessageId(),DeliveryStatus.SENT);
    }

    @Override
    public String getType() {
        return "push";
    }
}