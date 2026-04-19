package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.DeliveryFactory;
import org.example.factory.PackagingFactory;
import org.example.enums.DeliveryType;
import org.example.model.PackageItem;
import org.example.service.Shipment;
import org.example.service.ShipmentService;
import org.example.utility.ErrorMessages;
@Slf4j
public class Main {
    public static void main(String[] args) {
        DeliveryFactory deliveryFactory = new DeliveryFactory();
        PackagingFactory packagingFactory = new PackagingFactory();
        ShipmentService service = new ShipmentService(packagingFactory,deliveryFactory);
        PackageItem item1 = new PackageItem(150);
        PackageItem item2 = new PackageItem(5);

       Shipment shipment1= service.ship(item1, DeliveryType.EXPRESS);

      log.info("Shipment current state:{}", shipment1.getCurrentState());
      shipment1.next();
      shipment1.next();
      log.info("Shipment current state:{}", shipment1.getCurrentState());
      shipment1.next();


    }
}