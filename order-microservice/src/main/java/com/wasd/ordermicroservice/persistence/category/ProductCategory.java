package com.wasd.ordermicroservice.persistence.category;

import com.wasd.ordermicroservice.persistence.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class ProductCategory {
    @Id
    private Long id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @OrderBy
    @JoinColumn(name = "parent_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductCategory parent = null;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ProductCategory> children = new HashSet<>();
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();

    public void addCategory(ProductCategory category) {
        children.add(category);
        category.setParent(this);
    }

    public void removeCategory(ProductCategory category) {
        children.remove(category);
        category.setParent(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCategory(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCategory(null);
    }
}
