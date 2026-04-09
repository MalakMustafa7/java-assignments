package org.example.service;
import org.example.model.CheckoutRequest;
import org.example.model.CheckoutResult;

public interface CheckoutService {
    CheckoutResult checkOut(CheckoutRequest checkoutRequest);
}
