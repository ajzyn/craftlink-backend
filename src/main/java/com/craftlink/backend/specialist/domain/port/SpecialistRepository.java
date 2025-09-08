package com.craftlink.backend.specialist.domain.port;

import com.craftlink.backend.specialist.domain.model.Specialist;

public interface SpecialistRepository {

  Specialist save(Specialist specialist);
}
