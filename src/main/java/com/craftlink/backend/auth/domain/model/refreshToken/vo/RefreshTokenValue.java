package com.craftlink.backend.auth.domain.model.refreshToken.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Base64;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record RefreshTokenValue(String value) {

  public RefreshTokenValue {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_REFRESH_TOKEN", "Refresh token is empty", Map.of());
    }
    try {
      Base64.getUrlDecoder().decode(value);
    } catch (IllegalArgumentException e) {
      throw new DomainException("INCORRECT_REFRESH_TOKEN", "Invalid refresh token format");
    }
  }
}
