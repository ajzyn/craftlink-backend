package com.craftlink.backend.specialist.adapter.out.persistence.read;

import com.craftlink.backend.specialist.application.port.in.query.shared.SpecialistProfileView;
import com.craftlink.backend.specialist.application.port.out.read.SpecialistQueryRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialistQueryRepositoryAdapter implements SpecialistQueryRepository {

  private final SpecialistQueryRepositoryJpa jpa;

  @Override
  public Optional<SpecialistProfileView> findById(UUID specialistId) {
    return Optional.empty();
  }
}
