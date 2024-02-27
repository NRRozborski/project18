package com.laguna.project18.WallapopGratuito.dto;

import com.laguna.project18.WallapopGratuito.model.Category;
import com.laguna.project18.WallapopGratuito.model.User;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private LocalDateTime uploaded;
    private LocalDateTime claimed;
    private Long weariness;
    private Integer rating;
    private User user;
    private Category category;
}