package org.example.factroy.imp;

import org.example.Validation.PaymentValidator;
import org.example.enums.PaymentType;
import org.example.factroy.PaymentFactory;
import org.example.service.CustomerService;
import org.example.strategy.payment.PaymentGateway;
import org.example.strategy.payment.CreditCard;
import org.example.strategy.payment.GiftCard;
import org.example.strategy.payment.PayPal;
import org.example.utility.ErrorMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PaymentFactoryImpl implements PaymentFactory {
    private final PaymentValidator paymentValidator;
    private final CustomerService customerService;

    private final Map<PaymentType, Supplier<PaymentGateway>> registry = new HashMap<>();

    public PaymentFactoryImpl(PaymentValidator paymentValidator,
                              CustomerService customerService) {
        this.paymentValidator = paymentValidator;
        this.customerService = customerService;

        register(PaymentType.CREDIT_CARD, () -> new CreditCard(paymentValidator));
        register(PaymentType.PAYPAL, () -> new PayPal(paymentValidator));
        register(PaymentType.GIFT_CARD, () -> new GiftCard(paymentValidator, customerService));
    }

    public void register(PaymentType type, Supplier<PaymentGateway> supplier) {
        registry.put(type, supplier);
    }


    @Override
    public PaymentGateway createPayment(PaymentType paymentType) {
        Supplier<PaymentGateway> supplier = registry.get(paymentType);
        if(supplier==null){
            throw new IllegalArgumentException(ErrorMessages.UNKNOWN_TYPE);
        }
        return supplier.get();
    }
}
