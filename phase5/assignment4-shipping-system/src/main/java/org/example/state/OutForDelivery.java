package org.example.state;
import org.example.service.Shipment;


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
