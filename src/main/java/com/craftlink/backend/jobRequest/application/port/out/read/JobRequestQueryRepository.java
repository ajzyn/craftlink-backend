package com.craftlink.backend.jobRequest.application.port.out.read;

import com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.JobRequestDetailsView;
import java.util.Optional;
import java.util.UUID;

public interface JobRequestQueryRepository {

  Optional<JobRequestDetailsView> findDetailsById(UUID id);
}
