package com.laguna.project18.WallapopGratuito.service;

import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.mapper.CategoryMapper;
import com.laguna.project18.WallapopGratuito.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(
                categoryMapper::toResponse
        ).toList();
    }

    public CategoryResponseDTO getCategoryById(Long id){
        return categoryMapper.toResponse(
                categoryRepository.findById(id)
                        .orElseThrow()
        );
    }
}
