package com.craftlink.backend.jobRequest.application.usecase;

import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestCommand;
import com.craftlink.backend.jobRequest.application.dto.CreateJobRequestResult;

public interface CreateJobRequestUseCase {

  CreateJobRequestResult handle(CreateJobRequestCommand cmd);
}
