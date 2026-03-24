package org.example.repositoy.impl;

import org.example.enums.DeliveryStatus;
import org.example.repositoy.NotificationMessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NotificationMessageRepositoryImpl implements NotificationMessageRepository {
    Map<Long, DeliveryStatus> messageStatusMap=new HashMap<>();

    public void saveMessageStatus(Long messageId, DeliveryStatus status) {
        messageStatusMap.put(messageId, status);
    }

    public Optional<DeliveryStatus> getMessageStatusById(Long messageId){
        return Optional.ofNullable(messageStatusMap.get(messageId));
    }
}
