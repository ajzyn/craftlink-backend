package com.craftlink.backend.jobRequest.application.port.usecase.getJobRequestDetails;

import com.craftlink.backend.jobRequest.adapter.web.dto.GetJobRequestDetailsDto;
import java.util.UUID;

public interface GetJobRequestDetailsUseCase {


  //TODO: return result, not a dto
  GetJobRequestDetailsDto handle(UUID id);
}
