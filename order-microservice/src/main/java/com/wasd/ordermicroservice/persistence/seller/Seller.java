package com.wasd.ordermicroservice.persistence.seller;

import com.wasd.ordermicroservice.persistence.order.OrderDetails;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", length = 300)
    private String description;
    @Column(name = "TIN", nullable = false, unique = true)
    private Integer tin;
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> orders = new HashSet<>();

    public void addOrderDetails(OrderDetails order) {
        orders.add(order);
        order.setSeller(this);
    }

    public void removeOrderDetails(OrderDetails order) {
        orders.remove(order);
        order.setSeller(null);
    }
}
