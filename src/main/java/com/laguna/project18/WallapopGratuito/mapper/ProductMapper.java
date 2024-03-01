package com.laguna.project18.WallapopGratuito.mapper;


import com.laguna.project18.WallapopGratuito.dto.ProductRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.ProductResponseDTO;

import com.laguna.project18.WallapopGratuito.model.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryMapper categorymapper;
    public ProductResponseDTO toResponse(Product product){
        return new ProductResponseDTO(
            product.getName(),
            product.getDescription(),
            product.getUploaded(),
            product.getClaimed(),
            product.getWeariness(),
            product.getRating(),
            product.getUser().getUsername(),
            categorymapper.toResponse(product.getCategory()),
        null
        );
    }

    public List<ProductResponseDTO> toResponse(List<Product> products){
        return products.stream().map(this::toResponse).toList();
    }

    public Product toModel(ProductRequestDTO productRequestDTO){
        return new Product(
                null,
               productRequestDTO.getName(),
               productRequestDTO.getDescription(),
               productRequestDTO.getUploaded(),
               productRequestDTO.getClaimed(),
               productRequestDTO.getWeariness(),
               productRequestDTO.getRating(),
               productRequestDTO.getUser(),
               productRequestDTO.getCategory(),
                null
        );
    }
}