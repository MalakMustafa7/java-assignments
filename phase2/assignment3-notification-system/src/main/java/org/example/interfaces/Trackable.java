package org.example.interfaces;

import org.example.enums.DeliveryStatus;

public interface Trackable {
    DeliveryStatus getDeliveryStatus(Long messageId);
}
