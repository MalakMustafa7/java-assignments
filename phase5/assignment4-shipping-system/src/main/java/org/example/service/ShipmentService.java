package org.example.service;

import lombok.AllArgsConstructor;
import org.example.strategy.delivery.Delivery;
import org.example.strategy.packaging.PackagingStrategy;
import org.example.factory.DeliveryFactory;
import org.example.factory.PackagingFactory;
import org.example.model.DeliveryType;
import org.example.model.PackageItem;

@AllArgsConstructor
public class ShipmentService {
    private PackagingFactory packagingFactory;
    private DeliveryFactory deliveryFactory;
    public void ship(PackageItem item, DeliveryType type){
      PackagingStrategy packagingStrategy = packagingFactory.getPackagingStrategy(item);
      Delivery delivery = deliveryFactory.getDelivery(type);
      Shipment shipment = new Shipment(item,packagingStrategy,delivery);
      shipment.process();
    }
}
