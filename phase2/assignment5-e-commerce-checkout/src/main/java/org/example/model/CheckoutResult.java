package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.enums.PaymentType;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
public class CheckoutResult {
    private List<CartItem> cartItems;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal discount;
    private BigDecimal total;
    private PaymentType paymentMethode;
    private boolean paymentResult;

}
