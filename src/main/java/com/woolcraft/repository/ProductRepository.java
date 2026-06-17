package com.woolcraft.repository;

import com.woolcraft.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByFeaturedTrueAndActiveTrue();
    List<Product> findByActiveTrueOrderByCreatedAtDesc();
    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY (SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.product = p AND r.approved = true) DESC")
    List<Product> findBestSellers();
    @Query("SELECT p FROM Product p WHERE p.active = true AND (LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%')))")
    List<Product> search(@Param("keyword") String keyword);
    List<Product> findByCategoryIdAndActiveTrue(Long categoryId);
    @Query("SELECT p.name FROM Product p WHERE p.active = true AND LOWER(p.name) LIKE LOWER(CONCAT(:prefix,'%'))")
    List<String> autocomplete(@Param("prefix") String prefix);
    long countByActiveTrue();
}
