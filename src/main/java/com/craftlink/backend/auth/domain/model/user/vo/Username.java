package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;

public record Username(String value) {

  public Username {
    if (value == null) {
      throw new DomainViolation("USERNAME_REQUIRED", "Username is required");
    }

  }
}
