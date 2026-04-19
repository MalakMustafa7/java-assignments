package org.example.state;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidShipmentStateException;
import org.example.model.PackageItem;
import org.example.service.Shipment;
import org.example.utility.ErrorMessages;


public class ReadyForDelivery implements State {

    @Override
    public void next(Shipment shipment) {
        shipment.setState(States.OUT_FOR_DELIVERY);
    }

    @Override
    public String name() {
        return "ReadyForDelivery";
    }
}
