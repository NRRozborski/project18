package com.laguna.project18.WallapopGratuito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRequestDTO {
    private String name;
    private String description;
}