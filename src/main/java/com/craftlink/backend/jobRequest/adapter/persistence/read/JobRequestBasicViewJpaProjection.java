package com.craftlink.backend.jobRequest.adapter.persistence.read;

import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public interface JobRequestBasicViewJpaProjection {

  UUID getId();

  JobRequestStatus getStatus();

  LocalDateTime getCreatedAt();

  UUID getRequesterId();

  UUID getServiceId();
}
