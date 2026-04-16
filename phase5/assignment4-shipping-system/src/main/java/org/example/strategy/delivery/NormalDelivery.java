package org.example.strategy.delivery;

import lombok.extern.slf4j.Slf4j;
import org.example.model.PackageItem;

@Slf4j
public class NormalDelivery implements Delivery {
    @Override
    public void deliver(PackageItem packageItem) {
        log.info("Delivering package with weight {} via Normal Delivery" ,packageItem.getWeight());
    }
}
