package org.example.factory;

import org.example.strategy.delivery.Delivery;
import org.example.strategy.delivery.ExpressDelivery;
import org.example.strategy.delivery.NormalDelivery;
import org.example.enums.DeliveryType;
import org.example.utility.ErrorMessages;

public class DeliveryFactory {
    public Delivery getDelivery(DeliveryType type) {
        return switch (type) {
            case DeliveryType.NORMAL -> new NormalDelivery();
            case DeliveryType.EXPRESS -> new ExpressDelivery();
            default -> throw new IllegalArgumentException(ErrorMessages.UNKNOWN_TYPE + type);
        };
        }

}
