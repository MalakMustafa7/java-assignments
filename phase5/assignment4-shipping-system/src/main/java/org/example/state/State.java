package org.example.state;
import org.example.service.Shipment;

public interface State {
    void next(Shipment shipment);
    String name();
}
