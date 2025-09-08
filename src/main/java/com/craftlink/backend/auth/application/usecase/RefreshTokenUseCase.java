package com.craftlink.backend.auth.application.usecase;

public interface RefreshTokenUseCase {

  String refresh(String rawRefreshToken);
}
