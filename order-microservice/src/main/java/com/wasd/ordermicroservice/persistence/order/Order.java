package com.wasd.ordermicroservice.persistence.order;


import com.wasd.ordermicroservice.data.order.OrderState;
import com.wasd.ordermicroservice.persistence.brand.Brand;
import com.wasd.ordermicroservice.persistence.category.ProductCategory;
import com.wasd.ordermicroservice.persistence.seller.Seller;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"seller", "category", "brand"})
@ToString(exclude = {"seller", "category", "brand"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 50, nullable = false)
    private OrderState state;
    @Column(name = "version", nullable = false)
    private Long version;
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
