package com.laguna.project18.WallapopGratuito.controller;

import com.laguna.project18.WallapopGratuito.dto.ProductResponseDTO;
import com.laguna.project18.WallapopGratuito.dto.TagResponseDTO;
import com.laguna.project18.WallapopGratuito.mapper.TagMapper;
import com.laguna.project18.WallapopGratuito.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    @GetMapping("/all")
    public ResponseEntity<List<TagResponseDTO>> getAllTags(@AuthenticationPrincipal OAuth2User oAuth2User){
//        System.out.println(oAuth2User);
        return ResponseEntity.ok(
                tagMapper.toResponse(
                        tagService.getAllTags()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> getTagById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                tagMapper.toResponse(
                        tagService.getTagById(id)
                )
        );
    }
}