package com.craftlink.backend.category.domain.model.categoryImage.vo;

import java.time.Instant;

public record UploadUpdatedAt(Instant value) {

  public UploadUpdatedAt {
    if (value != null && value.isAfter(Instant.now())) {
      throw new IllegalArgumentException("UploadCompletedAt cannot be in the future");
    }
  }

  public static UploadUpdatedAt now() {
    return new UploadUpdatedAt(Instant.now());
  }
}