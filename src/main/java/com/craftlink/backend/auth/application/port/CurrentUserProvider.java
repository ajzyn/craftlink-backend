package com.craftlink.backend.auth.application.port;

import com.craftlink.backend.auth.adapter.security.SpringSecurityCurrentUserProvider.CurrentUserContext;


//TODO: move it somewhere
public interface CurrentUserProvider {

  CurrentUserContext getCurrentUser();
}
