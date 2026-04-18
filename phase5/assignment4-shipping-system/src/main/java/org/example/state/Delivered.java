package org.example.state;

import org.example.exception.InvalidShipmentStateException;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;

public class Delivered implements State{
    @Override
    public void pack(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.CANNOT_PACK);
    }

    @Override
    public void ship(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.CANNOT_SHIP);

    }

    @Override
    public void deliver(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.CANNOT_DELIVER);
    }
}
