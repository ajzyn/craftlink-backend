package com.craftlink.backend.category.domain.model.categoryImage.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;

public record FileSize(long value) {

  public FileSize {
    if (value <= 0) {
      throw new DomainException("FILE_SIZE_INVALID", "File size must be > 0");
    }
  }
}