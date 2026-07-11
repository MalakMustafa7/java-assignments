package org.example;

public class EnrichedOrder {
    private Order order;
    private String productName;
    private String category;
    public EnrichedOrder(Order order, String productName, String category) {
        this.order = order;
        this.productName = productName;
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public Order getOrder() {
        return order;
    }

    public String getProductName() {
        return productName;
    }
}
