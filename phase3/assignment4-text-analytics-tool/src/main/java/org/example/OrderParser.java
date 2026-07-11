package org.example;

import java.time.LocalDate;
import java.util.Optional;

public class OrderParser {
    public static Optional<Order> parseOrder(String line){
        try {
            String [] parts = line.split(",");
            String orderId = parts[0].trim();
            String customerId = parts[1].trim();
            String productId = parts[2].trim();
            int quantity = Integer.parseInt(parts[3].trim());
            double unitPrice = Double.parseDouble(parts[4].trim());
            LocalDate orderDate = LocalDate.parse(parts[5].trim());
            String status = parts[6].trim();
            Order order = new Order(
                    orderId,
                    customerId,
                    productId,
                    quantity,
                    unitPrice,
                    orderDate,
                    status
            );

            return Optional.of(order);

        }catch (Exception e){
            return Optional.empty();
        }
    }
}
