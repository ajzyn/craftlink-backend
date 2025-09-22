package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;

public record Password(String value) {

  public Password {
    if (value == null) {
      throw new DomainException("EMPTY_PASSWORD", "Password cannot be empty");
    }
  }
}
