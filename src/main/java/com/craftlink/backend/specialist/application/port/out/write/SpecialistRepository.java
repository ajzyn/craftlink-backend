package com.craftlink.backend.specialist.application.port.out.write;

import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import java.util.Optional;

public interface SpecialistRepository {

  Specialist save(Specialist specialist);

  Optional<Specialist> findById(SpecialistId specialist);
}
