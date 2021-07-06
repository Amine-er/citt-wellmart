package com.citt.wellmart.repositories;

import com.citt.wellmart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String product);

    List<Product> findByCategoryId(Long id);
}
