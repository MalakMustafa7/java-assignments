package org.example.repositoy;

import org.example.enums.DeliveryStatus;

import java.util.Optional;

public interface NotificationMessageRepository {
    void saveMessageStatus(Long messageId, DeliveryStatus status);
    Optional<DeliveryStatus> getMessageStatusById(Long messageId);
}
