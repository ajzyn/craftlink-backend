package com.craftlink.backend.jobRequest.application.port.out.write;

import com.craftlink.backend.jobRequest.domain.model.jobRequest.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestId;
import java.util.Optional;


public interface JobRequestRepository {

  JobRequest save(JobRequest jobRequest);

  Optional<JobRequest> findById(JobRequestId id);
}
