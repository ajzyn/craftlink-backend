package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;

public interface SecureTokenGenerator {

  RefreshTokenValue generateBase64Url(int bytes);
}
