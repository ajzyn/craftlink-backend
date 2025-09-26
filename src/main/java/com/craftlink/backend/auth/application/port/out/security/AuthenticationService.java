package com.craftlink.backend.auth.application.port.out.security;

import com.craftlink.backend.shared.domain.vo.Credentials;

public interface AuthenticationService {

  AuthenticationResult authenticate(Credentials credentials);
}
