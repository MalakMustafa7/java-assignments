package org.example.state;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidShipmentStateException;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;
@Slf4j
public class OutForDelivery implements State{
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
        shipment.setState(States.DELIVERED);
    }
}
