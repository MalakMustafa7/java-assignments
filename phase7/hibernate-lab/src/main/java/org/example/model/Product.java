package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    private BigDecimal price;
    @ManyToOne()
    @JoinColumn(name = "category_id") // product is the owing side
    private Category category;
    @OneToMany(mappedBy = "product")//product is the inverse side
    private List<OrderItem> orderItems;

}
