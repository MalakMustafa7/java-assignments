package org.example;

import org.example.factory.DeliveryFactory;
import org.example.factory.PackagingFactory;
import org.example.enums.DeliveryType;
import org.example.model.PackageItem;
import org.example.service.ShipmentService;
import org.example.utility.ErrorMessages;

public class Main {
    public static void main(String[] args) {
        DeliveryFactory deliveryFactory = new DeliveryFactory();
        PackagingFactory packagingFactory = new PackagingFactory();
        ShipmentService service = new ShipmentService(packagingFactory,deliveryFactory);
        PackageItem item1 = new PackageItem(150);
        PackageItem item2 = new PackageItem(5);

        service.ship(item1, DeliveryType.EXPRESS);
        service.ship(item2,DeliveryType.NORMAL);


    }
}