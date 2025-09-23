package com.craftlink.backend.shared.domain.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainException("INCORRECT_USER_ID",
          "Incorrect User id",
          Map.of("value", "null"));
    }
  }

  public static UserId newId() {
    return new UserId(UUID.randomUUID());
  }
}
