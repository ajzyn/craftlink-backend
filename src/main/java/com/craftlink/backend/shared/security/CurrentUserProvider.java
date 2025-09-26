package com.craftlink.backend.shared.security;

import java.util.UUID;


public interface CurrentUserProvider {

  CurrentUserContext getCurrentUser();

  default boolean isSpecialist() {
    return getCurrentUser().specialistId() != null;
  }

  default boolean isClient() {
    return getCurrentUser().clientId() != null;
  }

  record CurrentUserContext(UUID userId, UUID clientId, UUID specialistId) {

  }
}
