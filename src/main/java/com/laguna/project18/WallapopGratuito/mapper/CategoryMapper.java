package com.laguna.project18.WallapopGratuito.mapper;

import com.laguna.project18.WallapopGratuito.dto.CategoryRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toModel(CategoryRequestDTO categoryRequestDTO){
        return new Category(
                0L,
                categoryRequestDTO.getName(),
                categoryRequestDTO.getDescription()
        );
    }
}
