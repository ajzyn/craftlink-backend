package com.craftlink.backend.auth.domain.model.refreshToken.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record RefreshTokenId(UUID value) {

  public RefreshTokenId {
    if (value == null) {
      throw new DomainException(
          "INCORRECT_REFRESH_TOKEN_ID",
          "Incorrect refresh token id",
          Map.of("value", "null")
      );
    }
  }

  public static RefreshTokenId newId() {
    return new RefreshTokenId(UUID.randomUUID());
  }
}
