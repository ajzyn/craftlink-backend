package com.craftlink.backend.category.domain.model.categoryImage;

import com.craftlink.backend.category.domain.model.categoryImage.vo.CategoryImageId;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ContentType;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileSize;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.category.domain.model.categoryImage.vo.Status;
import com.craftlink.backend.category.domain.model.categoryImage.vo.UploadUpdatedAt;
import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryImage {

  private final CategoryImageId id;
  private final ImageKey imageKey;
  private final FileName fileName;
  private final FileSize fileSize;
  private final ContentType contentType;
  private final UserId userId;
  private final Status status;
  private final UploadUpdatedAt uploadUpdatedAt;

  private CategoryImage(
      CategoryImageId id,
      ImageKey imageKey,
      FileName fileName,
      FileSize fileSize,
      ContentType contentType,
      UserId userId,
      Status status,
      UploadUpdatedAt uploadUpdatedAt) {
    this.id = id;
    this.imageKey = imageKey;
    this.fileName = fileName;
    this.fileSize = fileSize;
    this.contentType = contentType;
    this.userId = userId;
    this.status = status;
    this.uploadUpdatedAt = uploadUpdatedAt;
  }

  public static CategoryImage create(ImageKey imageKey,
      FileName fileName,
      FileSize fileSize,
      UserId userId,
      ContentType contentType,
      List<String> allowedExtensions,
      List<String> allowedContentTypes,
      long maxSize) {

    validate(fileName, fileSize, contentType, allowedExtensions, allowedContentTypes, maxSize);
    return new CategoryImage(CategoryImageId.newId(), imageKey, fileName, fileSize, contentType, userId,
        Status.UPLOADING, null);
  }

  private static void validate(FileName fileName,
      FileSize fileSize,
      ContentType contentType,
      List<String> allowedExtensions,
      List<String> allowedContentTypes,
      long maxSize) {
    if (!allowedExtensions.contains(fileName.extension())) {
      throw new DomainException("EXTENSION_NOT_ALLOWED", "Extension not allowed: " + fileName.extension());
    }
    if (!allowedContentTypes.contains(contentType.value().toLowerCase())) {
      throw new DomainException("CONTENT_TYPE_NOT_ALLOWED", "Content type not allowed: " + contentType.value());
    }
    if (fileSize.value() > maxSize) {
      throw new DomainException("FILE_TOO_BIG", "File size exceeds " + maxSize + " bytes");
    }
  }

  public static CategoryImage rehydrate(
      CategoryImageId id,
      ImageKey imageKey,
      FileName fileName,
      FileSize fileSize,
      ContentType contentType,
      UserId userId,
      Status status,
      UploadUpdatedAt uploadUpdatedAt) {
    return new CategoryImage(id, imageKey, fileName, fileSize, contentType, userId, status, uploadUpdatedAt);
  }

  public CategoryImage markAsCompleted() {
    return new CategoryImage(id, imageKey, fileName, fileSize, contentType, userId, Status.COMPLETED, uploadUpdatedAt);
  }

  public CategoryImage markAsFailed() {
    return new CategoryImage(id, imageKey, fileName, fileSize, contentType, userId, Status.FAILED, uploadUpdatedAt);
  }
}