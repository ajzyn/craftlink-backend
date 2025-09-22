package com.craftlink.backend.jobRequest.application.query;

import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.GetJobRequestDetailsUseCase;
import com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.JobRequestDetailsView;
import com.craftlink.backend.jobRequest.application.port.out.read.JobRequestQueryRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetJobRequestDetailsHandler implements GetJobRequestDetailsUseCase {

  private final JobRequestQueryRepository repo;

  @Override
  public JobRequestDetailsView handle(UUID id) {
    return repo.findDetailsById(id).orElseThrow(() -> new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND));
  }
}
