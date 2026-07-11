package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.Status;

import java.math.BigDecimal;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order",
            orphanRemoval = true,
             cascade = CascadeType.ALL) // order is the inverse side
    private List<OrderItem> orderItems;
    @ManyToOne // order is the owning side
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
