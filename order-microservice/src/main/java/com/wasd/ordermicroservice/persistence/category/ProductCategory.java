package com.wasd.ordermicroservice.persistence.category;

import com.wasd.ordermicroservice.persistence.order.OrderDetails;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"parent", "children", "orders"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @OrderBy
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parent = null;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ProductCategory> children = new HashSet<>();
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderDetails> orders = new HashSet<>();

    public void addCategory(ProductCategory category) {
        children.add(category);
        category.setParent(this);
    }

    public void removeCategory(ProductCategory category) {
        children.remove(category);
        category.setParent(null);
    }

    public void addOrderDetails(OrderDetails order) {
        orders.add(order);
        order.setCategory(this);
    }

    public void removeOrderDetails(OrderDetails order) {
        orders.remove(order);
        order.setCategory(null);
    }
}
