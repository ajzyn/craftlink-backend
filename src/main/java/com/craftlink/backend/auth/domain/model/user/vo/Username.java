package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;

public record Username(String value) {

  public Username {
    if (value == null) {
      throw new DomainException("USERNAME_REQUIRED", "Username is required");
    }

  }
}
