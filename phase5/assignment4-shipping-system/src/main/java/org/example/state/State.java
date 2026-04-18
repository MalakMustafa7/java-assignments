package org.example.state;

import org.example.model.PackageItem;
import org.example.service.Shipment;

public interface State {
    void pack(Shipment shipment);
    void ship(Shipment shipment);
    void deliver(Shipment shipment);
}
