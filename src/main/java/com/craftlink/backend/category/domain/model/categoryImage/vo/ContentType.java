package com.craftlink.backend.category.domain.model.categoryImage.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record ContentType(String value) {

  public ContentType {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("CONTENT_TYPE_INVALID", "Content type cannot be empty");
    }
  }
}