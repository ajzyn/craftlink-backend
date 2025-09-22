package com.craftlink.backend.jobRequest.application.port.in.command.createJobRequest;

public interface CreateJobRequestUseCase {

  CreateJobRequestResult handle(CreateJobRequestCommand cmd);
}
