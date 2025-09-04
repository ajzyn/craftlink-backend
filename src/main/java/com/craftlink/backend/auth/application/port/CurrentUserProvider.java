package com.craftlink.backend.auth.application.port;

import com.craftlink.backend.auth.adapters.security.SpringSecurityCurrentUserProvider.CurrentUserContext;

public interface CurrentUserProvider {

  CurrentUserContext getCurrentUser();
}
