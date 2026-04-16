package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.PackageItem;
import org.example.strategy.delivery.Delivery;
import org.example.strategy.packaging.PackagingStrategy;

@AllArgsConstructor
@Getter
public class Shipment {
    private PackageItem packageItem;
    PackagingStrategy packagingStrategy;
    Delivery delivery;

    public void process(){
        packagingStrategy.pack(packageItem);
        delivery.deliver(packageItem);
    }
}
