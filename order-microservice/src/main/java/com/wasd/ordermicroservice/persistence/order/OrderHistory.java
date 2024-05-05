package com.wasd.ordermicroservice.persistence.order;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_history")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Type(ListArrayType.class)
    @Column(name = "products", nullable = false, columnDefinition = "bigint[]")
    private Set<Long> productIndexes = new HashSet<>();
    @Column(name = "description", length = 300, nullable = false)
    private String description;
    @CreationTimestamp
    @Column(name = "operation_time", nullable = false)
    private Instant operationTime;
}
