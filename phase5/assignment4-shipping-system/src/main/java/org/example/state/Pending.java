package org.example.state;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidShipmentStateException;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;


public class Pending implements State{
    @Override
    public void pack(Shipment shipment) {
        shipment.applyPackaging();
        shipment.setState(States.READY_FOR_DELIVERY);

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
