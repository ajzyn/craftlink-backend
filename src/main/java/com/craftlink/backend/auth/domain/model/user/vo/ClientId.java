package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record ClientId(UUID value) {

  public ClientId {
    if (value == null) {
      throw new DomainViolation("INCORRECT_CLIENT_ID",
          "Incorrect client id",
          Map.of("value", "null"));
    }
  }
}
