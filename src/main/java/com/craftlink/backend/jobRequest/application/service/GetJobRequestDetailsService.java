package com.craftlink.backend.jobRequest.application.service;

import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.jobRequest.adapter.web.dto.GetJobRequestDetailsDto;
import com.craftlink.backend.jobRequest.application.port.JobRequestReadRepository;
import com.craftlink.backend.jobRequest.application.usecase.GetJobRequestDetailsUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetJobRequestDetailsService implements GetJobRequestDetailsUseCase {

  private final JobRequestReadRepository repo;


  @Override
  public GetJobRequestDetailsDto handle(UUID id) {
    var jr = repo.findDetailsById(id);

    if (jr.isEmpty()) {
      throw new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND);
    }

    return new GetJobRequestDetailsDto(jr.get().id());
  }
}
