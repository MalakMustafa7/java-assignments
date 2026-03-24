package org.example.model;

import lombok.Data;

@Data
public class Customer {
    private final Long customerId;
    private String name;
    private String region;
}
