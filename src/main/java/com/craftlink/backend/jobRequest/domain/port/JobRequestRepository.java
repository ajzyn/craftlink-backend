package com.craftlink.backend.jobRequest.domain.port;

import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import java.util.Optional;
import java.util.UUID;

public interface JobRequestRepository {

  JobRequest save(JobRequest jobRequest);

  Optional<JobRequest> findById(JobRequestId id);

  boolean existsActiveByUserId(UUID requesterId);
}
