package com.craftlink.backend.category.services;

import com.craftlink.backend.category.entities.CategoryImageEntity;
import com.craftlink.backend.category.properties.S3CategoryImageUploadProperties;
import com.craftlink.backend.category.repositories.CategoryImageRepository;
import com.craftlink.backend.config.aws.dtos.PresignedUploadRequestDto;
import com.craftlink.backend.config.aws.dtos.PresignedUploadResponseDto;
import com.craftlink.backend.config.aws.properties.PresignedUrlArgs;
import com.craftlink.backend.config.aws.services.S3Service;
import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryImageService {

    private final S3Service s3Service;
    private final S3CategoryImageUploadProperties categoryImageProperties;
    private final CategoryImageRepository categoryImageRepository;


    @Transactional
    public PresignedUploadResponseDto createUploadImageSession(PresignedUploadRequestDto requestDto) {

        var presignedUrl = s3Service.generatePresignedUploadUrl(
            PresignedUrlArgs.builder()
                .allowedFileExtensions(categoryImageProperties.getAllowedFileExtensions())
                .allowedContentTypes(categoryImageProperties.getAllowedContentTypes())
                .prefix(categoryImageProperties.getPrefix())
                .maxSize(categoryImageProperties.getMaxSizeBytes())
                .request(requestDto)
                .build()
        );

        var categoryImage = CategoryImageEntity.builder()
            .imageKey(presignedUrl.getImageKey())
            .contentType(requestDto.getContentType())
            .fileSize(requestDto.getFileSize())
            .originalFileName(requestDto.getFileName())
            .build();

        categoryImageRepository.save(categoryImage);

        log.info("Sesja uploadu utworzona. ImageKey: {}", presignedUrl.getImageKey());

        return presignedUrl;
    }

    @Transactional
    public void markUploadCompleted(String imageKey) {
        var image = categoryImageRepository.findByImageKey(imageKey)
            .orElseThrow(() -> new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND));

        if (!s3Service.imageExists(imageKey)) {
            markAsFailedAndSave(image);
            throw new BusinessException(ExceptionCode.AWS_FILE_NOT_FOUND);
        }

        image.markAsCompleted();
        categoryImageRepository.save(image);
        log.info("Upload oznaczony jako zakończony: {}", imageKey);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markAsFailedAndSave(CategoryImageEntity image) {
        image.markAsFailed();
        categoryImageRepository.save(image);
    }
}
