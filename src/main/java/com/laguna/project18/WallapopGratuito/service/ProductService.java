package com.laguna.project18.WallapopGratuito.service;

import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.dto.ProductRequestDTO;
import com.laguna.project18.WallapopGratuito.model.Product;
import com.laguna.project18.WallapopGratuito.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product postProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}