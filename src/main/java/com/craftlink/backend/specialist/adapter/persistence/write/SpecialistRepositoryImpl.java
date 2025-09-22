package com.craftlink.backend.specialist.adapter.persistence.write;

import com.craftlink.backend.specialist.adapter.persistence.mapper.SpecialistPersistenceMapper;
import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.port.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialistRepositoryImpl implements SpecialistRepository {

  private final SpringDataSpecialistJpa jpa;
  private final SpecialistPersistenceMapper mapper;

  @Override
  public Specialist save(Specialist specialist) {
    var entity = mapper.toEntity(specialist);
    jpa.save(entity);
    return mapper.toDomain(entity);
  }
}
