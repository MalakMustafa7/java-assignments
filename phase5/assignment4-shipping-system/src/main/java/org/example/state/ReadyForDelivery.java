package org.example.state;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidShipmentStateException;
import org.example.model.PackageItem;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;

@AllArgsConstructor
@Slf4j
public class ReadyForDelivery implements State {
    @Override
    public void pack(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.CANNOT_PACK);
    }

    @Override
    public void ship(Shipment shipment) {
        shipment.applyDelivery();
        shipment.setState(States.OUT_FOR_DELIVERY);

    }

    @Override
    public void deliver(Shipment shipment) {
        throw new InvalidShipmentStateException(ErrorMessages.CANNOT_DELIVER);
    }
}
