package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.application.dto.UserSnapshot;

public interface AccessTokenGenerator {

  String generateAccessToken(UserSnapshot user);
}
