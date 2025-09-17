package com.craftlink.backend.category.domain.model.category.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record CategoryName(String value) {

  public CategoryName {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation(
          "CATEGORY_NAME_EMPTY",
          "Category name cannot be empty",
          Map.of("value", value)
      );
    }
    if (value.length() > 100) {
      throw new DomainViolation(
          "CATEGORY_NAME_TOO_LONG",
          "Category name too long",
          Map.of("length", value.length())
      );
    }
  }
}