package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.application.dto.UserSnapshot;

public interface Authenticator {

  UserSnapshot authenticate(String email, String rawPassword);
}
