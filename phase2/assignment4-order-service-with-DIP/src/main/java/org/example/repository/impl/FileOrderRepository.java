package org.example.repository.impl;

import org.example.enums.Status;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.OrderRepository;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileOrderRepository implements OrderRepository {
    private final File file;

    public FileOrderRepository(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Order order) {
        List<Order> orders = findAll();

        orders.removeIf(o -> o.getOrderId().equals(order.getOrderId()));
        orders.add(order);

        writeAll(orders);
    }

    @Override
    public Optional<Order> findById(String id) {
        return findAll().stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return findAll().stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .toList();
    }

    @Override
    public List<Order> findByStatus(Status status) {
        return findAll().stream()
                .filter(o -> o.getOrderStatus() == status)
                .toList();
    }

    @Override
    public List<Order> findAll() {

        List<Order> orders = new ArrayList<>();

        if (!file.exists()) return orders;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                orders.add(parseOrder(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public void deleteById(String id) {

        List<Order> orders = findAll();

        orders.removeIf(o -> o.getOrderId().equals(id));

        writeAll(orders);
    }

    private void writeAll(List<Order> orders) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Order order : orders) {
                writer.write(serializeOrder(order));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String serializeOrder(Order order) {

        String items = order.getOrderItems().stream()
                .map(i -> i.getName() + ":" + i.getQuantity() + ":" + i.getPrice())
                .reduce((a, b) -> a + "|" + b)
                .orElse("");

        return String.join(",",
                order.getOrderId(),
                order.getCustomerId(),
                order.getOrderStatus().name(),
                order.getCreatedAt().toString(),
                items
        );
    }

    private Order parseOrder(String line) {

        String[] parts = line.split(",");

        String orderId = parts[0];
        String customerId = parts[1];
        Status status = Status.valueOf(parts[2]);
        LocalDateTime createdAt = LocalDateTime.parse(parts[3]);

        List<OrderItem> items = new ArrayList<>();

        if (parts.length > 4) {
            String[] itemParts = parts[4].split("\\|");

            for (String item : itemParts) {

                String[] data = item.split(":");

                String name = data[0];
                int qty = Integer.parseInt(data[1]);
                BigDecimal price = new BigDecimal(data[2]);

                items.add(new OrderItem(qty,name, price));
            }
        }

        return new Order(
                orderId,
                customerId,
                items,
                status,
                createdAt
        );
    }
}
