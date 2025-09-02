package com.craftlink.backend.jobRequest.application.port;

import com.craftlink.backend.jobRequest.application.dto.JobRequestBasicView;
import java.util.Optional;
import java.util.UUID;

public interface JobRequestReadRepository {

  Optional<JobRequestBasicView> findDetailsById(UUID id);
}
