package com.wasd.ordermicroservice.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(value = """
            with order_id as (
                insert into orders (state, version, seller_id, category_id, brand_id)
                    values ('PENDING', 0, :sellerId, :categoryId, :brandId) returning id)
                        
            insert
            into order_details (id, price, created_on, customer_id)
            values ((select id from order_id), :price, now(), :customerId);""", nativeQuery = true)
    void create(@Param("sellerId") Long sellerId,
                @Param("categoryId") Long categoryId,
                @Param("brandId") Integer brandId,
                @Param("price") BigDecimal price,
                @Param("customerId") Long customerId);
}