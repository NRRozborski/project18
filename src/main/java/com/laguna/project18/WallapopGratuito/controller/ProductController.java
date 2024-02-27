package com.laguna.project18.WallapopGratuito.controller;

import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.dto.ProductRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.ProductResponseDTO;
import com.laguna.project18.WallapopGratuito.mapper.ProductMapper;
import com.laguna.project18.WallapopGratuito.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@AuthenticationPrincipal OAuth2User oAuth2User){
//        System.out.println(oAuth2User);
        return ResponseEntity.ok(
                productMapper.toResponse(
                        productService.getAllProducts()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                productMapper.toResponse(
                        productService.getProductById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> postProduct(
            @RequestBody ProductRequestDTO product
    ) {
        return ResponseEntity.ok(
                productMapper.toResponse(
                        productService.postProduct(
                                productMapper.toModel(product)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(
            @PathVariable Long id
    ){
        productService.deleteById(id);
    }
}