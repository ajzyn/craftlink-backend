package com.craftlink.backend.auth.application.port.in.command.refreshToken;

import com.craftlink.backend.auth.application.port.in.command.shared.LoginResult;

public interface RefreshTokenUseCase {

  LoginResult handle(RefreshTokenCommand cmd);
}
