package org.example.service;

import lombok.AllArgsConstructor;
import org.example.strategy.delivery.Delivery;
import org.example.strategy.packaging.PackagingStrategy;
import org.example.factory.DeliveryFactory;
import org.example.factory.PackagingFactory;
import org.example.enums.DeliveryType;
import org.example.model.PackageItem;

@AllArgsConstructor
public class ShipmentService {
    private PackagingFactory packagingFactory;
    private DeliveryFactory deliveryFactory;

    public Shipment ship(PackageItem item, DeliveryType type){
      Shipment shipment = create(item,type);
      shipment.process();
      return shipment;
    }

    private Shipment create(PackageItem item, DeliveryType type) {
        PackagingStrategy packaging = packagingFactory.getPackagingStrategy(item);
        Delivery delivery = deliveryFactory.getDelivery(type);
        return new Shipment(item, packaging, delivery);
    }
}
