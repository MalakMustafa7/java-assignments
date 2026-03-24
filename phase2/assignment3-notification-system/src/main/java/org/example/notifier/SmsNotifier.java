package org.example.notifier;

import lombok.extern.slf4j.Slf4j;
import org.example.enums.DeliveryStatus;
import org.example.interfaces.Sendable;
import org.example.interfaces.Trackable;
import org.example.interfaces.Notifier;
import org.example.model.NotificationMessage;
import org.example.repositoy.NotificationMessageRepository;

@Slf4j
public class SmsNotifier implements Sendable, Trackable, Notifier {
    private final NotificationMessageRepository repository;

    public SmsNotifier(NotificationMessageRepository repository){
        this.repository = repository;
    }
    @Override
    public void send(NotificationMessage message) {
        log.info("Sending SMS: {}", message.getContent());
        message.setDeliveryStatus(DeliveryStatus.SENT);
        repository.saveMessageStatus(message.getMessageId(), DeliveryStatus.SENT);
    }

    @Override
    public DeliveryStatus getDeliveryStatus(Long messageId) {
        return repository.getMessageStatusById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("Message ID not found: " + messageId));
    }

    @Override
    public String getType() {
        return "sms";
    }
}
