package com.wasd.productmicroservice.persistence.category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    @EntityGraph(attributePaths = {"parent"})
    Optional<ProductCategory> findByTitle(String title);
}
