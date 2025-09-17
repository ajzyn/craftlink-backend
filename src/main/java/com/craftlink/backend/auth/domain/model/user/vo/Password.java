package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;

public record Password(String value) {

  public Password {
    if (value == null) {
      throw new DomainViolation("EMPTY_PASSWORD", "Password cannot be empty");
    }
  }
}
