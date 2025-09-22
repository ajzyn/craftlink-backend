package com.craftlink.backend.category.domain.model.categoryImage.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record FileName(String value) {

  public FileName {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("FILE_NAME_EMPTY", "File name cannot be empty");
    }
  }

  public String extension() {
    int lastDot = value.lastIndexOf('.');
    if (lastDot == -1) {
      throw new DomainException("FILE_EXTENSION_MISSING", "File extension missing");
    }
    return value.substring(lastDot).toLowerCase();
  }
}