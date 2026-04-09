package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.enums.DiscountType;
import org.example.enums.PaymentType;
@Getter
@AllArgsConstructor
public class CheckoutRequest {
    Long customerId;
    PaymentType paymentType;
    DiscountType discountType;
    String currency;
}
