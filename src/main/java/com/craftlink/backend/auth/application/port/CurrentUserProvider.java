package com.craftlink.backend.auth.application.port;

import java.util.UUID;

public interface CurrentUserProvider {

  UUID getCurrentUserId();
}
