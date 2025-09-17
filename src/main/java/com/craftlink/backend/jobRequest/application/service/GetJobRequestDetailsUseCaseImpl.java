package com.craftlink.backend.jobRequest.application.service;

import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.jobRequest.adapter.web.dto.GetJobRequestDetailsDto;
import com.craftlink.backend.jobRequest.application.port.repository.JobRequestQueryRepository;
import com.craftlink.backend.jobRequest.application.port.usecase.getJobRequestDetails.GetJobRequestDetailsUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetJobRequestDetailsUseCaseImpl implements GetJobRequestDetailsUseCase {

  private final JobRequestQueryRepository repo;


  @Override
  public GetJobRequestDetailsDto handle(UUID id) {
    var jr = repo.findDetailsById(id);

    if (jr.isEmpty()) {
      throw new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND);
    }

    return new GetJobRequestDetailsDto(jr.get().id());
  }
}
