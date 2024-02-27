package com.laguna.project18.WallapopGratuito.service;

import com.laguna.project18.WallapopGratuito.model.Product;
import com.laguna.project18.WallapopGratuito.model.Tag;
import com.laguna.project18.WallapopGratuito.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow();
    }
}