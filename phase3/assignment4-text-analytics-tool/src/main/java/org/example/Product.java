package org.example;

public class Product {
   private String productId;
   private String name;
   private String category;

    public Product(String productId,String name,String category) {
        this.productId=productId;
        this.name = name;
        this.category=category;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
