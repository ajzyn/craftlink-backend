package com.craftlink.backend.auth.domain.model.security.vo;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;


public record AuthenticationResult(
    UUID userId,
    String username,
    UserType userType,
    Set<String> authorities,
    boolean authenticated
) {

  public AuthenticationResult {
    authorities = Collections.unmodifiableSet(authorities);
  }

  public static AuthenticationResult success(UUID userId, String username,
      UserType userType, Set<String> authorities) {
    return new AuthenticationResult(userId, username, userType, authorities, true);
  }

  public static AuthenticationResult failure() {
    return new AuthenticationResult(null, null, null, Collections.emptySet(), false);
  }
}