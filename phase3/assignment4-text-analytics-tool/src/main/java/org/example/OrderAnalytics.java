package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class OrderAnalytics {
    public void process(String ordersPath, String productsPath, LocalDate from, LocalDate to) throws Exception{
        Map<String, Product> productMap =
                CsvParser.readProducts(productsPath)
                        .collect(toMap(Product::getProductId, p -> p));
        List<Order> filteredOrders =
                CsvParser.readOrders(ordersPath)
                        .filter(o -> o.getStatus().equals("COMPLETED"))
                        .filter(o -> !o.getOrderDate().isBefore(from) && !o.getOrderDate().isAfter(to))
                        .toList();

        filteredOrders.stream()
                .map(o -> {
                    Product p = productMap.get(o.getProductId());

                    return new EnrichedOrder(
                            o,
                            p != null ? p.getName() : "UNKNOWN",
                            p != null ? p.getCategory() : "UNKNOWN"
                    );
                })
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                orders -> {

                                    long totalOrders = orders.size();

                                    double totalSpend = filteredOrders.stream()
                                            .mapToDouble(order->order.getQuantity()*order.getUnitPrice())
                                             .sum();

                                    double avg = totalSpend / totalOrders;

                                    String topCategory = orders.stream()
                                            .collect(Collectors.groupingBy(
                                                    EnrichedOrder::getCategory,
                                                    Collectors.summingInt(EnrichedOrder::getQuantity)
                                            ))
                                            .entrySet().stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getKey)
                                            .orElse("N/A");

                                    return new CustomerStats(
                                            totalOrders,
                                            totalSpend,
                                            avg,
                                            topCategory
                                    );
                                }
                        )
                ));



    }
}
//
//    public List<Order> getCompletedOrders(Path path, LocalDate startDate,LocalDate endDate) throws IOException {
//        return Files.lines(path)
//                .map(OrderParser::parseOrder)
//                .flatMap(Optional::stream)
//                .filter(order->order.getStatus().equals("COMPLETED "))
//                .filter(order -> !order.getOrderDate().isBefore(startDate)
//                                       && !order.getOrderDate().isAfter(endDate))
//                .toList();
//    }
//    public static Map<Long,CustomerStats>aggregateByCustomer(Path path) throws IOException{
//        return Files.lines(path)
//                .map(OrderParser::parseOrder)
//                .flatMap(Optional::stream)
//                .collect(Collectors.groupingBy(Order::getCustomerId,
//                        Collectors.collectingAndThen(
//                                Collectors.toList(),
//                                orders->{
//                                    int totalOrders = orders.size();
//                                    double totalSpent = orders.stream()
//                                            .mapToDouble(order->order.getQuantity()*order.getUnitPrice())
//                                            .sum();
//                                    double averageOrderValue = totalSpent/totalOrders;
//                                    String mostPurchasedCategory = orders.stream()
//                                            .collect(Collectors.groupingBy())
//                                }
//                        ))
//    }
//}
