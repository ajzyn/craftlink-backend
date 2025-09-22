package com.craftlink.backend.auth.application.port.out.external;

import java.util.UUID;

public interface ClientProfilePort {

  void createClientForUser(UUID userId);
}
