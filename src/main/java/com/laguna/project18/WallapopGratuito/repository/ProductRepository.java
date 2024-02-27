package com.laguna.project18.WallapopGratuito.repository;

import com.laguna.project18.WallapopGratuito.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}