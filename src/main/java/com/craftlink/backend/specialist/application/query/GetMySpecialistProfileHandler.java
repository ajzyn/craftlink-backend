package com.craftlink.backend.specialist.application.query;

import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.custom.SecurityException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import com.craftlink.backend.specialist.application.port.in.query.getMySpecialistProfile.GetMySpecialistProfileUseCase;
import com.craftlink.backend.specialist.application.port.in.query.shared.SpecialistProfileView;
import com.craftlink.backend.specialist.application.port.out.read.SpecialistQueryRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetMySpecialistProfileHandler implements GetMySpecialistProfileUseCase {

  private final SpecialistQueryRepository repository;
  private final CurrentUserProvider currentUserProvider;

  @Override
  @Transactional(readOnly = true)
  public SpecialistProfileView handle() {
    if (!currentUserProvider.isSpecialist()) {
      throw new SecurityException(ExceptionCode.FORBIDDEN, "Current user is not a specialist");
    }

    var specialistId = currentUserProvider.getCurrentUser().specialistId();

    return repository.findById(specialistId).orElseThrow(() -> new BusinessException(
        ExceptionCode.RESOURCE_NOT_FOUND,
        Map.of("specialistId", specialistId.toString())
    ));
  }
}
