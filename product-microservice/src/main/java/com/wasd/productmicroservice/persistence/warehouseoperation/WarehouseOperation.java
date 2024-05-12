package com.wasd.productmicroservice.persistence.warehouseoperation;

import com.wasd.productmicroservice.persistence.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse_operations")
@ToString(exclude = {"product"})
@EqualsAndHashCode(exclude = {"product"})
public class WarehouseOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "description", nullable = false)
    private String description;
    @CreationTimestamp
    private Instant operationTime;
}
