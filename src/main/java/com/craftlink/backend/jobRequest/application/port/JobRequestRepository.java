package com.craftlink.backend.jobRequest.application.port;

import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import java.util.Optional;


//TODO: where should i place it
public interface JobRequestRepository {

  JobRequest save(JobRequest jobRequest);

  Optional<JobRequest> findById(JobRequestId id);
}
