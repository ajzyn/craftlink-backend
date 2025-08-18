package com.craftlink.backend.category.services;

import com.craftlink.backend.category.dtos.CategoryDetailsDto;
import com.craftlink.backend.category.dtos.CategorySummaryDto;
import com.craftlink.backend.category.repositories.CategoryRepository;
import com.craftlink.backend.config.aws.services.S3Service;
import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final S3Service s3Service;

    public List<CategorySummaryDto> getCategories() {
        var categories = categoryRepository.findAll();

        return categories.stream()
            .map(category -> modelMapper.map(category, CategorySummaryDto.class))
            .toList();
    }

    public CategoryDetailsDto getCategoryDetailsBySlug(String slug) {
        var category = categoryRepository.findBySlugWithServicesAndImage(slug)
            .orElseThrow(() -> new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND));

        return modelMapper.map(category, CategoryDetailsDto.class);
    }


    public void deleteCategory(Integer categoryId) {
        var category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND));

        if (category.getImage() != null) {
            try {
                s3Service.deleteImage(category.getImage().getImageKey());
            } catch (Exception e) {
                log.error("Błąd usuwania obrazu z S3: {}", category.getImage().getImageKey(), e);
            }
        }

        categoryRepository.delete(category);
    }
}
