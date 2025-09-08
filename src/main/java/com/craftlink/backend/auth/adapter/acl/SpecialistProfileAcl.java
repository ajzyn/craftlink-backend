package com.craftlink.backend.auth.adapter.acl;

import com.craftlink.backend.specialist.application.public_.usecase.CreateSpecialistUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecialistProfileAcl {

  private final CreateSpecialistUseCase createSpecialistUseCase;

  public UUID createSpecialistForUser(UUID userId) {
    return createSpecialistUseCase.handle(userId);
  }
}
