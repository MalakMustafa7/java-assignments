package org.example.model;


import lombok.Getter;
import lombok.Setter;
import org.example.enums.DeliveryStatus;

import java.util.concurrent.atomic.AtomicLong;
@Setter
@Getter
public class NotificationMessage {
    private final Long messageId;
    private String content;
    private DeliveryStatus deliveryStatus;

    private static final AtomicLong counter = new AtomicLong(0);

    public NotificationMessage(String content){
        this.messageId = counter.incrementAndGet();
        this.content = content;
        this.deliveryStatus = DeliveryStatus.PENDING;
    }


}
