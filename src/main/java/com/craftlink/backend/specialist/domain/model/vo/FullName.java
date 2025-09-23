package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record FullName(String value) {

  public FullName {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_FULL_NAME",
          "Full name cannot be empty",
          Map.of("value", "null or blank"));
    }
    if (value.length() > 100) {
      throw new DomainException("FULL_NAME_TOO_LONG",
          "Full name cannot exceed 100 characters",
          Map.of("value", value));
    }
  }
}