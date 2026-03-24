package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.example.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
   private  String orderId;
   private String customerId;
   private List<OrderItem>orderItems;
   private Status orderStatus;
   private LocalDateTime createdAt;



   public Order(String customerId,List<OrderItem> orderItems){
      this.orderId = UUID.randomUUID().toString().substring(0,5);
      this.orderStatus = Status.PENDING;
      this.createdAt = LocalDateTime.now();
      this.customerId = customerId;
      this.orderItems = orderItems;
   }


}
