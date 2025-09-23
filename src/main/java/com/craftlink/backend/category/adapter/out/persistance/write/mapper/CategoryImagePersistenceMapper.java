package com.craftlink.backend.category.adapter.out.persistance.write.mapper;

import com.craftlink.backend.category.adapter.out.persistance.CategoryImageEntity;
import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.CategoryImageId;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ContentType;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileSize;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.category.domain.model.categoryImage.vo.Status;
import com.craftlink.backend.category.domain.model.categoryImage.vo.UploadUpdatedAt;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryImagePersistenceMapper {

  public CategoryImage toDomain(CategoryImageEntity e) {
    return CategoryImage.rehydrate(
        new CategoryImageId(e.getId()),
        new ImageKey(e.getImageKey()),
        new FileName(e.getOriginalFileName()),
        new FileSize(e.getFileSize()),
        new ContentType(e.getContentType()),
        new UserId(e.getUserId()),
        Status.valueOf(e.getImageStatus().name()),
        Optional.ofNullable(e.getUploadCompletedAt()).map(UploadUpdatedAt::new).orElse(null)
    );
  }

  public CategoryImageEntity toEntity(CategoryImage d) {
    return CategoryImageEntity.builder()
        .id(d.getId().value())
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