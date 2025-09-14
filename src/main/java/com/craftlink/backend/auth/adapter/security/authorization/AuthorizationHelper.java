package com.craftlink.backend.auth.adapter.security.authorization;

import com.craftlink.backend.auth.adapter.security.model.UserPrincipal;
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
}
