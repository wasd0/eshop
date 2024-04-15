package com.wasd.ordermicroservice.persistence.seller;

import com.wasd.ordermicroservice.persistence.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", length = 300)
    private String description;
    @Column(name = "TIN", nullable = false, unique = true)
    private Integer tin;
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setSeller(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setSeller(null);
    }
}
