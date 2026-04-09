package org.example.pipeline.handlers;

import org.example.pipeline.CheckoutContext;
import org.example.pipeline.CheckoutHandler;
import org.example.service.NotificationService;
import org.example.utility.NotificationMessages;

public class NotificationHandler extends CheckoutHandler {
    private final NotificationService notificationService;
    public NotificationHandler(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @Override
    protected void process(CheckoutContext context) {
        String message = String.format(NotificationMessages.ORDER_CONFIRMED,
                                       context.getOrder().getOrderId(),
                                       context.getOrder().getCustomerId(),
                                       context.getOrder().getTotal());
        notificationService.sendNotification(message);
    }
}
