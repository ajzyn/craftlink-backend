package com.craftlink.backend.auth.application.port.out.security;

import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;

public interface RefreshTokenGenerator {

  RefreshTokenValue generateBase64Url(int bytes);
}
