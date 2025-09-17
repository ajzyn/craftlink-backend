package com.craftlink.backend.category.infrastructure.persistance.mapper;

import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ContentType;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileSize;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.category.domain.model.categoryImage.vo.Status;
import com.craftlink.backend.category.domain.model.categoryImage.vo.UploadUpdatedAt;
import com.craftlink.backend.category.infrastructure.persistance.CategoryImageEntity;
import com.craftlink.backend.shared.vo.UserId;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryImagePersistenceMapper {

  default CategoryImage toDomain(CategoryImageEntity e) {
    return CategoryImage.rehydrate(
        new ImageKey(e.getImageKey()),
        new FileName(e.getOriginalFileName()),
        new FileSize(e.getFileSize()),
        new ContentType(e.getContentType()),
        new UserId(e.getUserId()),
        Status.valueOf(e.getImageStatus().name()),
        Optional.ofNullable(e.getUploadCompletedAt()).map(UploadUpdatedAt::new).orElse(null)
    );
  }

  default CategoryImageEntity toEntity(CategoryImage d) {
    return CategoryImageEntity.builder()
        .imageKey(d.getImageKey().value())
        .originalFileName(d.getFileName().value())
        .fileSize(d.getFileSize().value())
        .contentType(d.getContentType().value())
        .userId(d.getUserId().value())
        .imageStatus(d.getStatus())
        .uploadCompletedAt(d.getUploadUpdatedAt() != null ? d.getUploadUpdatedAt().value() : null)
        .build();
  }
}