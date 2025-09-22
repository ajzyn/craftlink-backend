package com.craftlink.backend.jobRequest.adapter.port.out.persistence.read;

import com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.JobRequestDetailsView;
import com.craftlink.backend.jobRequest.application.port.out.read.JobRequestQueryRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRequestQueryRepositoryHandler implements JobRequestQueryRepository {

  private final JobRequestQueryRepositoryJpa jpa;

  @Override
  public Optional<JobRequestDetailsView> findDetailsById(UUID id) {
    return jpa.findDetailsById(id);
  }
}
