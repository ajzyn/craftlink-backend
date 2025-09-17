package com.craftlink.backend.category.domain.model.category.vo;


import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record ServiceDescription(String value) {

  public ServiceDescription {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation(
          "SERVICE_DESCRIPTION_EMPTY",
          "Service description cannot be empty",
          Map.of("value", value)
      );
    }
    if (value.length() > 500) {
      throw new DomainViolation(
          "SERVICE_DESCRIPTION_TOO_LONG",
          "Service description too long",
          Map.of("length", value.length())
      );
    }
  }
}