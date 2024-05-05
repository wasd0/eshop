package com.wasd.productmicroservice.persistence.product;

import com.wasd.productmicroservice.persistence.brand.Brand;
import com.wasd.productmicroservice.persistence.category.ProductCategory;
import com.wasd.productmicroservice.persistence.seller.Seller;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"seller", "category", "brand"})
@ToString(exclude = {"seller", "category", "brand"})
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
}
