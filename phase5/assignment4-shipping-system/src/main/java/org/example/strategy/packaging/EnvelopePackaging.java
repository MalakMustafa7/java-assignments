package org.example.strategy.packaging;

import lombok.extern.slf4j.Slf4j;
import org.example.model.PackageItem;

@Slf4j
public class EnvelopePackaging implements PackagingStrategy {
    @Override
    public void pack(PackageItem item) {
        log.info("Packed item with weight {} in Envelope", item.getWeight());
    }
}
