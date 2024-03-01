package com.laguna.project18.WallapopGratuito.dto;

import com.laguna.project18.WallapopGratuito.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TagResponseDTO {
    private String name;
    private Set<Product> products;
}