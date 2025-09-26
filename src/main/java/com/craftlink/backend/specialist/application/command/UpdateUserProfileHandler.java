package com.craftlink.backend.specialist.application.command;

import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.custom.SecurityException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import com.craftlink.backend.specialist.application.port.in.command.updateUserProfile.UpdateUserProfileCommand;
import com.craftlink.backend.specialist.application.port.in.command.updateUserProfile.UpdateUserProfileUseCase;
import com.craftlink.backend.specialist.application.port.out.write.SpecialistRepository;
import com.craftlink.backend.specialist.domain.model.vo.Bio;
import com.craftlink.backend.specialist.domain.model.vo.FullName;
import com.craftlink.backend.specialist.domain.model.vo.Location;
import com.craftlink.backend.specialist.domain.model.vo.PhoneNumber;
import com.craftlink.backend.specialist.domain.model.vo.ServiceId;
import com.craftlink.backend.specialist.domain.model.vo.SpecialistId;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileHandler implements UpdateUserProfileUseCase {

  private final SpecialistRepository repository;
  private final CurrentUserProvider currentUserProvider;

  @Override
  @Transactional
  public void handle(UpdateUserProfileCommand cmd) {
    if (!currentUserProvider.isSpecialist()) {
      throw new SecurityException(ExceptionCode.FORBIDDEN, "Current user is not a specialist");
    }

    var specialistId = currentUserProvider.getCurrentUser().specialistId();

    var specialist = repository.findById(new SpecialistId(specialistId))
        .orElseThrow(() -> new BusinessException(
            ExceptionCode.RESOURCE_NOT_FOUND,
            Map.of("specialistId", specialistId.toString())
        ));

    var services = cmd.serviceIds().stream().map(ServiceId::new).collect(Collectors.toSet());

    specialist.updateProfile(
        new FullName(cmd.fullName()),
        new PhoneNumber(cmd.phoneNumber()),
        new Location(cmd.location()),
        new Bio(cmd.bio()),
        services,
        cmd.specialistType()
    );

    repository.save(specialist);
  }
}
