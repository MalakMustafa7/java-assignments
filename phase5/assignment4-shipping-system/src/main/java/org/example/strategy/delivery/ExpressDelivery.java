package org.example.strategy.delivery;

import lombok.extern.slf4j.Slf4j;
import org.example.model.PackageItem;

@Slf4j
public class ExpressDelivery implements Delivery {
    @Override
    public void deliver(PackageItem packageItem) {
        log.info("Delivering package with weight {} via Express Delivery" ,packageItem.getWeight());
    }
}
