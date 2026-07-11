package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class CsvParser {
    public static Stream<Order> readOrders(String path) throws Exception{
        return Files.lines(Path.of(path))
                .skip(1)
                .map(CsvParser::parseOrder)
                .flatMap(Optional::stream);
    }
    public static Stream<Product> readProducts(String path) throws Exception{
        return Files.lines(Path.of(path))
                .skip(1)
                .map(CsvParser::parseProduct)
                .flatMap(Optional::stream);
    }
    private static Optional<Order> parseOrder(String line){
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

    private static Optional<Product> parseProduct(String line) {
        try {
            String[] parts = line.split(",");

            return Optional.of(new Product(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim()
            ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
