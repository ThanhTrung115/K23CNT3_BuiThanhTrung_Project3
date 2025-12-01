package com.treeree.shop.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public Page<Product> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Product getBySlug(String slug) {
        return repo.findBySlug(slug);
    }

    public Page<Product> search(String keyword, int page, int size) {
        return repo.findByNameContainingIgnoreCase(keyword, PageRequest.of(page, size));
    }

    public Page<Product> byCategory(Long categoryId, int page, int size) {
        return repo.findByCategory_Id(categoryId, PageRequest.of(page, size));
    }

    public Page<Product> byBrand(Long brandId, int page, int size) {
        return repo.findByBrand_Id(brandId, PageRequest.of(page, size));
    }
}