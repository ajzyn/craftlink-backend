package com.craftlink.backend.jobRequest.adapter.persistence.read;

import com.craftlink.backend.jobRequest.application.dto.JobRequestBasicView;
import com.craftlink.backend.jobRequest.application.port.JobRequestReadRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRequestReadRepositoryAdapter implements JobRequestReadRepository {

  private final SpringDataJobRequestDetailsJpa jpa;

  @Override
  public Optional<JobRequestBasicView> findDetailsById(UUID id) {
    return jpa.findViewById(id).map(v ->
        new JobRequestBasicView(v.getId(), v.getStatus(), v.getCreatedAt(), v.getRequesterId(),
            v.getServiceId())
    );
  }
}
