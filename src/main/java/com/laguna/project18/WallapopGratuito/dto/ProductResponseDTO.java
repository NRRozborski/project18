package com.laguna.project18.WallapopGratuito.dto;

import com.laguna.project18.WallapopGratuito.model.Category;
import com.laguna.project18.WallapopGratuito.model.Tag;
import com.laguna.project18.WallapopGratuito.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class ProductResponseDTO {
    private String name;
    private String description;
    private LocalDateTime uploaded;
    private LocalDateTime claimed;
    private Long weariness;
    private Integer rating;
    private String user;
    private CategoryResponseDTO category;
    private Set<Tag> tags;

}