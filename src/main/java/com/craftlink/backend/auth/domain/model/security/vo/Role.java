package com.craftlink.backend.auth.domain.model.security.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record Role(String value) {

  public Role {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INVALID_ROLE", "Role value cannot be null or blank");
    }
  }

  public static Role of(String value) {
    return new Role(value);
  }
}
