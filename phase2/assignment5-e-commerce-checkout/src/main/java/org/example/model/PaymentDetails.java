package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PaymentDetails {
    private BigDecimal amount;
    private String currency;
    private Long customerId;
}