package com.craftlink.backend.category.domain.model.category.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record ServiceName(String value) {

  public ServiceName {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation(
          "SERVICE_NAME_EMPTY",
          "Service name cannot be empty",
          Map.of("value", value)
      );
    }
    if (value.length() > 100) {
      throw new DomainViolation(
          "SERVICE_NAME_TOO_LONG",
          "Service name too long",
          Map.of("length", value.length())
      );
    }
  }
}