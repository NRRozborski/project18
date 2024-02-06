package com.laguna.project18.WallapopGratuito.controller;

import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getCategories(){
        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                categoryService.getCategoryById(id)
        );
    }
}
