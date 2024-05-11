package com.wasd.productmicroservice.persistence.category;

import com.wasd.productmicroservice.persistence.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"parent", "children", "products"})
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
    private Set<Product> products = new HashSet<>();

    public void addChildCategory(ProductCategory child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChildCategory(ProductCategory child) {
        children.remove(child);
        child.setParent(null);
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }
}
