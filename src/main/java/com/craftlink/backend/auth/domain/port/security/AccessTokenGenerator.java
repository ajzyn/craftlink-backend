package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.application.dto.UserView;

public interface AccessTokenGenerator {

  String generateAccessToken(UserView user);
}
