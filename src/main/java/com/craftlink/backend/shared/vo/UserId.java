package com.craftlink.backend.shared.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainException("INCORRECT_REFRESH_TOKEN_ID",
          "Incorrect refresh token id",
          Map.of("value", "null"));
    }
  }

  public static UserId newId() {
    return new UserId(UUID.randomUUID());
  }
}
