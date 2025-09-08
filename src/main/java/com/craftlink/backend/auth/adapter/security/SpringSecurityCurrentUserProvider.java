package com.craftlink.backend.auth.adapter.security;

import com.craftlink.backend.auth.adapter.config.models.UserPrincipal;
import com.craftlink.backend.auth.application.port.CurrentUserProvider;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityCurrentUserProvider implements CurrentUserProvider {

  @Override
  public CurrentUserContext getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      throw new IllegalStateException("No authenticated user");
    }
    var principal = (UserPrincipal) auth.getPrincipal();

    UUID userId = principal.getId();
    UUID clientId = principal.getClientId();
    UUID specialistId = principal.getSpecialistId();

    return new CurrentUserContext(userId, clientId, specialistId);
  }

  public record CurrentUserContext(
      UUID userId,
      UUID clientId,
      UUID specialistId
  ) {

  }
}
