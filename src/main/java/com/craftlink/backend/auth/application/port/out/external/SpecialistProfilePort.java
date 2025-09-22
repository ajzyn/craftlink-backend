package com.craftlink.backend.auth.application.port.out.external;

import java.util.UUID;

public interface SpecialistProfilePort {

  UUID createSpecialistProfile(UUID userId);
}
