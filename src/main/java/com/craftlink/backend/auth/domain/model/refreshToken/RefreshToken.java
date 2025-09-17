package com.craftlink.backend.auth.domain.model.refreshToken;

import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenId;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.shared.vo.UserId;
import java.time.Instant;
import lombok.Getter;

@Getter
public final class RefreshToken {

  private final RefreshTokenId id;
  private final UserId userId;
  private final RefreshTokenValue token;
  private final ExpirationDate expirationDate;

  private RefreshToken(RefreshTokenId id, UserId userId, RefreshTokenValue token,
      ExpirationDate expirationDate) {
    this.id = id;
    this.userId = userId;
    this.token = token;
    this.expirationDate = expirationDate;
  }

  public static RefreshToken create(UserId userId, RefreshTokenValue token,
      ExpirationDate expirationDate) {
    return new RefreshToken(RefreshTokenId.newId(), userId, token, expirationDate);
  }

  public static RefreshToken rehydrate(RefreshTokenId id, UserId userId, RefreshTokenValue token,
      ExpirationDate expirationDate) {
    return new RefreshToken(id, userId, token, expirationDate);
  }

  public boolean isActive(Instant now) {
    return expirationDate.value().isAfter(now);
  }
}
