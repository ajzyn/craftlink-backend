package com.craftlink.backend.auth.application.port.out.security;

import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserView;

public interface AccessTokenGenerator {

  String generateAccessToken(UserView user);
}
