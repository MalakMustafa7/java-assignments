package org.example.enums;

import java.util.Arrays;

public enum TaxType {
    US("US"),
    EU("EU"),
    NONE("NONE");

    private final String region;

    TaxType(String region) {
        this.region = region;
    }

    public static TaxType fromRegion(String region) {
        return Arrays.stream(values())
                .filter(t -> t.region.equalsIgnoreCase(region))
                .findFirst()
                .orElse(NONE);
    }
}
