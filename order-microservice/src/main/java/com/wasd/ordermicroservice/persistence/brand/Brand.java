package com.wasd.ordermicroservice.persistence.brand;

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
@Table(name = "brands")
public class Brand {
    @Id
    private Integer id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setBrand(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setBrand(null);
    }
}
