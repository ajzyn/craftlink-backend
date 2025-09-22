package com.craftlink.backend.auth.application.port.in.command.refreshToken;

import com.craftlink.backend.auth.application.port.in.command.shared.AuthResult;

public interface RefreshTokenUseCase {

  AuthResult handle(RefreshTokenCommand cmd);
}
