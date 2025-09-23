package com.craftlink.backend.specialist.adapter.out.persistence.write;

import com.craftlink.backend.specialist.adapter.out.persistence.mapper.SpecialistPersistenceMapper;
import com.craftlink.backend.specialist.application.port.out.write.SpecialistRepository;
import com.craftlink.backend.specialist.domain.model.Specialist;
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
