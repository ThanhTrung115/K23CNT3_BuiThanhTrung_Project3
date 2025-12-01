package com.treeree.shop.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySlug(String slug);
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);
    Page<Product> findByBrand_Id(Long brandId, Pageable pageable);
}