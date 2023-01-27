package com.troop.orderservice.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "custcode", nullable = false)
    private String custcode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLineItems> orderLineItems;

}

