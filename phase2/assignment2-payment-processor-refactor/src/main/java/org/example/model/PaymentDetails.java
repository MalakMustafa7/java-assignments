package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.validation.PaymentDetailsValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PaymentDetails {
    private BigDecimal amount;
    private String currency;
    private Map<String,String> metadata;

}
