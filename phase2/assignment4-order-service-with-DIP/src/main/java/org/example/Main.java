package org.example;


import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.repository.OrderRepository;
import org.example.repository.impl.FileOrderRepository;
import org.example.repository.impl.InMemoryOrderRepository;
import org.example.service.Impl.OrderServiceImpl;
import org.example.service.OrderService;


import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderRepository repo = new InMemoryOrderRepository();
        OrderRepository fileOrderRepository = new FileOrderRepository("orders.txt");
        OrderService service = new OrderServiceImpl(fileOrderRepository);

        List<OrderItem> items = List.of(
                new OrderItem( 1,"Laptop", new BigDecimal("1000")),
                new OrderItem( 2,"Mouse", new BigDecimal("50"))
        );

        Order order = service.placeOrder("cust1", items);
        System.out.println("Total = " + service.getOrderSummary(order.getOrderId()));
        //service.updateStatus(order.getOrderId(), Status.CONFIRMED);

        System.out.println(order);
    }
}