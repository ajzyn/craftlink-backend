package com.craftlink.backend.jobRequest.adapter.persistence.write;

import com.craftlink.backend.jobRequest.adapter.persistence.mapper.JobRequestPersistenceMapper;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestId;
import com.craftlink.backend.jobRequest.domain.model.valueObjects.JobRequestStatus;
import com.craftlink.backend.jobRequest.domain.port.JobRequestRepository;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRequestRepositoryAdapter implements JobRequestRepository {

  private final SpringDataJobRequestJpa jpa;
  private final JobRequestPersistenceMapper mapper;

  @Override
  public JobRequest save(JobRequest jobRequest) {
    var entity = mapper.toEntity(jobRequest);
    var savedJobRequest = jpa.save(entity);
    return mapper.toDomain(savedJobRequest);
  }

  @Override
  public Optional<JobRequest> findById(JobRequestId id) {
    return jpa.findById(id.value()).map(mapper::toDomain);
  }

  @Override
  public boolean existsActiveByUserId(UUID requesterId) {
    var active = EnumSet.of(JobRequestStatus.ACTIVE);
    return jpa.existsByRequesterIdAndStatusIn(requesterId, active);
  }
}
