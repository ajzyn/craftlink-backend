package com.craftlink.backend.category.domain.model.category.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record CategoryDescription(String value) {

  public CategoryDescription {
    if (StringUtils.isBlank(value)) {
      throw new DomainException(
          "CATEGORY_DESCRIPTION_EMPTY",
          "Category description cannot be empty",
          Map.of("value", value)
      );
    }
    if (value.length() > 1000) {
      throw new DomainException(
          "CATEGORY_DESCRIPTION_TOO_LONG",
          "Category description is too long",
          Map.of("length", value.length())
      );
    }
  }
}