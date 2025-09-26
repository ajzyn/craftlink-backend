package com.craftlink.backend.specialist.adapter.out.persistence.write;

import com.craftlink.backend.specialist.adapter.out.persistence.mapper.SpecialistPersistenceMapper;
import com.craftlink.backend.specialist.application.port.out.write.SpecialistRepository;
import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialistRepositoryHandler implements SpecialistRepository {

  private final SpringDataSpecialistJpa jpa;

  @Override
  public Specialist save(Specialist specialist) {
    var entity = SpecialistPersistenceMapper.toEntity(specialist);
    var specialistEntity = jpa.save(entity);
    return SpecialistPersistenceMapper.toDomain(specialistEntity);
  }

  @Override
  public Optional<Specialist> findById(SpecialistId userId) {
    return jpa.findById(userId.value()).map(SpecialistPersistenceMapper::toDomain);
  }

}
