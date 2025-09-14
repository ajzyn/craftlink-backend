package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.domain.model.security.vo.AuthenticationResult;
import com.craftlink.backend.auth.domain.model.security.vo.Credentials;

public interface AuthenticationService {

  AuthenticationResult authenticate(Credentials credentials);
}
