package com.craftlink.backend.jobRequest.domain.port;

import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import java.util.Optional;


public interface JobRequestRepository {

  JobRequest save(JobRequest jobRequest);

  Optional<JobRequest> findById(JobRequestId id);
}
