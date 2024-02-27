package com.laguna.project18.WallapopGratuito.mapper;

import com.laguna.project18.WallapopGratuito.dto.CategoryRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.CategoryResponseDTO;
import com.laguna.project18.WallapopGratuito.dto.TagRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.TagResponseDTO;
import com.laguna.project18.WallapopGratuito.model.Category;
import com.laguna.project18.WallapopGratuito.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagMapper {
    public TagResponseDTO toResponse(Tag tag){
        return new TagResponseDTO(
                tag.getName(),
                tag.getProducts()
        );
    }

    public List<TagResponseDTO> toResponse(List<Tag> tags){
        return tags.stream().map(this::toResponse).toList();
    }

    public Tag toModel(TagRequestDTO tagRequestDTO){
        return new Tag(
                null,
                tagRequestDTO.getName(),
                null,
                tagRequestDTO.getProducts()
        );
    }
}