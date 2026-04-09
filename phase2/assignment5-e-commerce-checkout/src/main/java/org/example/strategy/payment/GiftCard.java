package org.example.strategy.payment;
import org.example.Validation.PaymentValidator;
import org.example.exception.InSufficientBalanceException;
import org.example.model.PaymentDetails;
import org.example.model.PaymentResult;
import org.example.service.CustomerService;
import org.example.utility.PaymentMessages;

public class GiftCard implements PaymentGateway {
    private final PaymentValidator paymentValidator;
    private final CustomerService customerService;
    public GiftCard(PaymentValidator paymentValidator,
                    CustomerService customerService){
        this.paymentValidator = paymentValidator;
        this.customerService = customerService;
    }
    @Override
    public PaymentResult processPayment(PaymentDetails paymentDetails) {
        try {
            paymentValidator.validatePaymentDetails(paymentDetails);
            customerService.deductBalance(paymentDetails.getCustomerId(),paymentDetails.getAmount());
            return new PaymentResult(true, PaymentMessages.GIFT_CARD_SUCCESS);
        }catch (InSufficientBalanceException | IllegalArgumentException e) {
            return new PaymentResult(false, e.getMessage());
        }

    }
}
