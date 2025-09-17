package com.craftlink.backend.jobRequest.application.port.repository;

import com.craftlink.backend.jobRequest.application.dto.JobRequestSummaryView;
import java.util.Optional;
import java.util.UUID;

public interface JobRequestQueryRepository {

  Optional<JobRequestSummaryView> findDetailsById(UUID id);
}
