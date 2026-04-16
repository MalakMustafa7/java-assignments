package org.example.strategy.delivery;

import org.example.model.PackageItem;

public interface Delivery {
    void deliver(PackageItem packageItem);
}
