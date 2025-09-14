package com.craftlink.backend.shared.security;

import java.util.UUID;


public interface CurrentUserProvider {

  CurrentUserContext getCurrentUser();

  record CurrentUserContext(UUID userId, UUID clientId, UUID specialistId) {

  }
}
