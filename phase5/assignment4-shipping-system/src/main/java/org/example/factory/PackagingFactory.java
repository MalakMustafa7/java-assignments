package org.example.factory;

import org.example.strategy.packaging.BoxPackaging;
import org.example.strategy.packaging.EnvelopePackaging;
import org.example.strategy.packaging.PackagingStrategy;
import org.example.model.PackageItem;

public class PackagingFactory {
    public PackagingStrategy getPackagingStrategy(PackageItem item){
        if (item.getWeight() >= 100) {
            return new BoxPackaging();
        } else {
            return new EnvelopePackaging();
        }
    }
}
