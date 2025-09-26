package com.craftlink.backend.specialist.application.port.out.read;

import com.craftlink.backend.specialist.application.port.in.query.shared.SpecialistProfileView;
import java.util.Optional;
import java.util.UUID;

public interface SpecialistQueryRepository {

  Optional<SpecialistProfileView> findById(UUID specialistId);
}
