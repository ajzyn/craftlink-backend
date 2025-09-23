package com.craftlink.backend.specialist.application.port.out.write;

import com.craftlink.backend.specialist.domain.model.Specialist;

public interface SpecialistRepository {

  Specialist save(Specialist specialist);
}
