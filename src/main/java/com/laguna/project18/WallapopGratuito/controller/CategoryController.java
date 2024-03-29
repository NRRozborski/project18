package com.laguna.project18.WallapopGratuito.controller;

import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(@AuthenticationPrincipal OAuth2User oAuth2User){
//        System.out.println(oAuth2User);
        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                categoryService.getCategoryById(id)
        );
    }

}