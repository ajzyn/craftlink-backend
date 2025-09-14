package com.craftlink.backend.auth.application.port.usecase;

import com.craftlink.backend.auth.application.dto.AuthResult;

public interface RefreshTokenUseCase {

  AuthResult handle(String rawRefreshToken);
}
