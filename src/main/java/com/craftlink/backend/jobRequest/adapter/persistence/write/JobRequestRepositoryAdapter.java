package com.craftlink.backend.jobRequest.adapter.persistence.write;

import com.craftlink.backend.jobRequest.adapter.persistence.mapper.JobRequestPersistenceMapper;
import com.craftlink.backend.jobRequest.domain.model.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestId;
import com.craftlink.backend.jobRequest.domain.port.JobRequestRepository;
import java.util.Optional;
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
    jpa.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<JobRequest> findById(JobRequestId id) {
    return jpa.findById(id.value()).map(mapper::toDomain);
  }
}
