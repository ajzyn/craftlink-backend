package com.craftlink.backend.jobRequest.adapter.persistence.read;

import com.craftlink.backend.jobRequest.application.dto.JobRequestSummaryView;
import com.craftlink.backend.jobRequest.application.port.repository.JobRequestQueryRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRequestQueryRepositoryAdapter implements JobRequestQueryRepository {

  private final SpringDataJobRequestDetailsJpa jpa;

  @Override
  public Optional<JobRequestSummaryView> findDetailsById(UUID id) {
    return jpa.findViewById(id).map(v ->
        new JobRequestSummaryView(v.getId(), v.getStatus(), v.getCreatedAt(), v.getRequesterId(),
            v.getServiceId())
    );
  }
}
