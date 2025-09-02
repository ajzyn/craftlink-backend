package com.craftlink.backend.auth.adapters.security;

import com.craftlink.backend.auth.application.port.CurrentUserProvider;
import com.craftlink.backend.config.security.models.UserPrincipal;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityCurrentUserProvider implements CurrentUserProvider {

  @Override
  public UUID getCurrentUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null || !auth.isAuthenticated()) {
      throw new IllegalStateException("No authenticated user");
    }
    return ((UserPrincipal) auth.getPrincipal()).getId();
  }
}
