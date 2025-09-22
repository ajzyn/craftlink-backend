package com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails;

import java.util.UUID;

public interface GetJobRequestDetailsUseCase {

  JobRequestDetailsView handle(UUID id);
}
