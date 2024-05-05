package com.wasd.productmicroservice.persistence.category;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Modifying
    @Query(value = "insert into categories (title, parent_category_id)" +
            " values (:title, (select id from categories where title = :parent))",
            nativeQuery = true)
    void save(@Param("title") String title,
              @Param("parent") String parentTitle);

    @EntityGraph(attributePaths = {"parent"})
    Optional<ProductCategory> findByTitle(String title);
}
