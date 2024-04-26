package com.wasd.ordermicroservice.persistence.order;

import com.wasd.ordermicroservice.data.order.Money;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@ToString(exclude = {"details"})
@EqualsAndHashCode(exclude = {"details"})
public class Order {
    @Id
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price", nullable = false))
    private Money price;
    @CreationTimestamp
    private Instant createdOn;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Order details;
}
