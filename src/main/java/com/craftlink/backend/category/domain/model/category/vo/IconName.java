package com.craftlink.backend.category.domain.model.category.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record IconName(String value) {

  public IconName {
    if (StringUtils.isBlank(value)) {
      throw new DomainException(
          "ICON_NAME_EMPTY",
          "Icon name cannot be empty",
          Map.of("value", value)
      );
    }
    if (value.length() > 100) {
      throw new DomainException(
          "ICON_NAME_TOO_LONG",
          "Icon name is too long",
          Map.of("length", value.length())
      );
    }
  }
}