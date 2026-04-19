package org.example.state;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidShipmentStateException;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;

public class OutForDelivery implements State{
    @Override
    public void next(Shipment shipment) {
        shipment.setState(States.DELIVERED);
    }

    @Override
    public String name() {
        return "OutForDelivery";
    }
}
