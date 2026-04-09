package org.example.pipeline;

import lombok.Getter;
import lombok.Setter;
import org.example.model.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class CheckoutContext {
   private final CheckoutRequest checkoutRequest;
   private final Customer customer;
   private final   Cart cart;
    BigDecimal subtotal;
    BigDecimal discount;
    BigDecimal tax;
    BigDecimal total;
    Order order;
    List<CartItem> cartItems;

    public CheckoutContext(CheckoutRequest request,Customer customer,Cart cart){
        this.checkoutRequest = request;
        this.customer = customer;
        this.cart = cart;
    }
}
