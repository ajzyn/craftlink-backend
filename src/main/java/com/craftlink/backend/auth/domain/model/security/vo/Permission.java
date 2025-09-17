package com.craftlink.backend.auth.domain.model.security.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record Permission(String value) {

  public Permission {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("INVALID_PERMISSION", "Permission value cannot be null or blank");
    }
  }

  public static Permission of(String value) {
    return new Permission(value);
  }
}