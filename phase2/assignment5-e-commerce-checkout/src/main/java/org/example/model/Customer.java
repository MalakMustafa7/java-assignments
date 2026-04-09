package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Customer {
    private final Long customerId;
    private String name;
    private String region;
    private BigDecimal balance;
}
