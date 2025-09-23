package com.craftlink.backend.specialist.application.command;

import com.craftlink.backend.shared.domain.vo.UserId;
import com.craftlink.backend.specialist.application.port.in.command.createSpecialistProfile.CreateSpecialistProfileCommand;
import com.craftlink.backend.specialist.application.port.in.command.createSpecialistProfile.CreateSpecialistProfileUseCase;
import com.craftlink.backend.specialist.application.port.out.write.SpecialistRepository;
import com.craftlink.backend.specialist.domain.model.Specialist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSpecialistProfileHandler implements CreateSpecialistProfileUseCase {

  private final SpecialistRepository specialistRepository;

  @Override
  public void handle(CreateSpecialistProfileCommand cmd) {
    var specialist = Specialist.create(new UserId(cmd.userId()));
    specialistRepository.save(specialist);
  }
}
