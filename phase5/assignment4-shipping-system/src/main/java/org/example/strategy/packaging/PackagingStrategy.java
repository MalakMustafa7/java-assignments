package org.example.strategy.packaging;

import org.example.model.PackageItem;

public interface PackagingStrategy {
    void pack(PackageItem item);
}
