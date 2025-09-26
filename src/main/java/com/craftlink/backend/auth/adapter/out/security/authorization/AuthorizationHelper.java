package com.craftlink.backend.auth.adapter.out.security.authorization;

import com.craftlink.backend.auth.adapter.out.security.model.UserPrincipal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/*
  Purpose: Integration with Spring Security's @PreAuthorize annotations
 */


@Component("authz")
@RequiredArgsConstructor
public class AuthorizationHelper {

  public boolean hasSpecialization(String code) {
    return getCurrentPrincipal()
        .map(user -> AuthorizationChecker.hasSpecialization(user.getOfferedServices(), code))
        .orElse(false);
  }

  public boolean hasAnySpecialization(String... codes) {
    return getCurrentPrincipal()
        .map(user -> AuthorizationChecker.hasAnySpecialization(user.getOfferedServices(), codes))
        .orElse(false);
  }

  private Optional<UserPrincipal> getCurrentPrincipal() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal user)) {
      return Optional.empty();
    }

    return Optional.of(user);
  }

  public boolean isSpecialist() {
    return getCurrentPrincipal().map(user -> user.getSpecialistId() != null).orElse(false);
  }

  public boolean isClient() {
    return getCurrentPrincipal().map(user -> user.getClientId() != null).orElse(false);
  }
}
