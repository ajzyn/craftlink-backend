package com.craftlink.backend.category.domain.model.categoryImage.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record ImageKey(String value) {

  public ImageKey {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("INVALID_IMAGE_KEY", "Image Key value cannot be empty");
    }
  }
}
