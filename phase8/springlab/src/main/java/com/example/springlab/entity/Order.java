package com.example.springlab.entity;

import com.example.springlab.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order",
            orphanRemoval = true,
            cascade = CascadeType.ALL) // order is the inverse side
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)// order is the owning side
    @JoinColumn(name = "customer_id")
    private Customer customer;
}