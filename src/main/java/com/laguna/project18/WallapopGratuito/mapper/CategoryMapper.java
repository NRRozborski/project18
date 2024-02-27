package com.laguna.project18.WallapopGratuito.mapper;

import com.laguna.project18.WallapopGratuito.dto.CategoryRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public List<CategoryResponseDTO> toResponse(List<Category> categories){
        return categories.stream().map(this::toResponse).toList();
    }

    public Category toModel(CategoryRequestDTO categoryRequestDTO){
        return new Category(
                null,
                categoryRequestDTO.getName(),
                categoryRequestDTO.getDescription(),
                null
        );
    }
}