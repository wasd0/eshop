package com.wasd.ordermicroservice.persistence.seller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    boolean existsByTin(Integer tin);
}
