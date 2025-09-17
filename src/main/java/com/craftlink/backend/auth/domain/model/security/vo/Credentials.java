package com.craftlink.backend.auth.domain.model.security.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record Credentials(String username, String password) {

  public Credentials {
    if (StringUtils.isBlank(username)) {
      throw new DomainViolation("INVALID_USERNAME", "Username cannot be null or blank");
    }
    if (StringUtils.isBlank(password)) {
      throw new DomainViolation("INVALID_PASSWORD", "Password cannot be null or blank");
    }
  }

  public static Credentials of(String username, String password) {
    return new Credentials(username, password);
  }
}