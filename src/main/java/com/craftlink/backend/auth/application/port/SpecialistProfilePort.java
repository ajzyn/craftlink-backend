package com.craftlink.backend.auth.application.port;

import java.util.UUID;

public interface SpecialistProfilePort {

  UUID createSpecialistProfile(UUID userId);
}
