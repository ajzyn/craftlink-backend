package com.craftlink.backend.auth.application.port;

import java.util.UUID;

public interface ClientProfilePort {

  UUID createClientForUser(UUID userId);
}
