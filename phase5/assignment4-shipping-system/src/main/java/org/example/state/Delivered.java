package org.example.state;

import org.example.exception.InvalidShipmentStateException;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;

public class Delivered implements State{
    @Override
    public void next(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.ALREADY_DELIVERED);
    }

    @Override
    public String name() {
        return "Delivered";
    }
}
