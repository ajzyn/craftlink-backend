package com.craftlink.backend.jobRequest.application.usecase;

import com.craftlink.backend.jobRequest.adapter.web.dto.GetJobRequestDetailsDto;
import java.util.UUID;

public interface GetJobRequestDetailsUseCase {

  GetJobRequestDetailsDto handle(UUID id);
}
