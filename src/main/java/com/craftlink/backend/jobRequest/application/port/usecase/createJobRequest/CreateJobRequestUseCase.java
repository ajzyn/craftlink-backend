package com.craftlink.backend.jobRequest.application.port.usecase.createJobRequest;

public interface CreateJobRequestUseCase {

  CreateJobRequestResult handle(CreateJobRequestCommand cmd);
}
