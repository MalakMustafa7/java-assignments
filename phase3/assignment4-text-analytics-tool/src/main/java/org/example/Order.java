package org.example;

import java.time.LocalDate;

public class Order {
    private  String orderId;
    private String  customerId;
    private String productId;
    private int quantity;
    private double unitPrice;
    private LocalDate orderDate;
    private String status;

    public Order(String  orderId,
                 String  customerId,
                 String productId,
                 int quantity,
                 double unitPrice,
                 LocalDate orderDate,
                 String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = orderDate;
        this.status = status;
    }


    public String  getOrderId() { return orderId; }
    public String  getCustomerId() { return customerId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }
}
