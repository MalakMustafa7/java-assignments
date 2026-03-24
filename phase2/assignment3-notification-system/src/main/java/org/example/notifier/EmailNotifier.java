package org.example.notifier;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.*;
import org.example.enums.DeliveryStatus;
import org.example.interfaces.*;
import org.example.interfaces.Templatable;
import org.example.interfaces.Trackable;
import org.example.model.NotificationMessage;
import org.example.model.ScheduledNotification;
import org.example.repositoy.NotificationMessageRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
@Getter
@Slf4j
public class EmailNotifier implements Schedulable, Sendable, Trackable, Templatable, Notifier {
    private final Queue<ScheduledNotification> scheduledQueue = new LinkedList<>();
    private final NotificationMessageRepository repository;

    public EmailNotifier(NotificationMessageRepository repository){
        this.repository = repository;
    }

    @Override
    public void schedule(NotificationMessage message, LocalDateTime dateTime) {
      scheduledQueue.add(new ScheduledNotification(message,dateTime));
        log.info("Push notification scheduled for: {}" ,dateTime);
    }

    @Override
    public void processPending() {
        log.info("process scheduled notification");
        LocalDateTime now = LocalDateTime.now();
        while (!scheduledQueue.isEmpty() && !scheduledQueue.peek().getScheduledTime().isAfter(now)) {
            send(scheduledQueue.poll().getMessage());
        }
    }

    @Override
    public void send(NotificationMessage message) {
        log.info("Sending Email: {}", message.getContent());
        message.setDeliveryStatus(DeliveryStatus.SENT);
        repository.saveMessageStatus(message.getMessageId(), DeliveryStatus.SENT);
    }

    @Override
   public String renderTemplate(String templateName, Map<String,String> vars) {
        StringBuilder rendered = new StringBuilder("Template: " + templateName);
        for (Map.Entry<String, String> entry : vars.entrySet()) {
            rendered.append(" | ").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return rendered.toString();
    }

    @Override
    public DeliveryStatus getDeliveryStatus(Long messageId) {
        return repository.getMessageStatusById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message ID not found: " + messageId));
    }

    @Override
    public String getType() {
        return "email";
    }
}
