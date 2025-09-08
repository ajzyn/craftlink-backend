package com.craftlink.backend.specialist.application.public_.usecase;

import com.craftlink.backend.specialist.domain.model.Specialist;
import com.craftlink.backend.specialist.domain.model.vo.UserId;
import com.craftlink.backend.specialist.domain.port.SpecialistRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateSpecialistUseCaseImpl implements CreateSpecialistUseCase {

  private final SpecialistRepository specialistRepository;

  @Override
  public UUID handle(UUID userId) {
    var specialist = Specialist.create(new UserId(userId));
    specialistRepository.save(specialist);

    return specialist.getId().value();
  }
}
