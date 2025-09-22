package com.craftlink.backend.jobRequest.adapter.port.out.persistence.write;

import com.craftlink.backend.jobRequest.adapter.port.out.persistence.write.mapper.JobRequestPersistenceMapper;
import com.craftlink.backend.jobRequest.application.port.out.write.JobRequestRepository;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.JobRequest;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobRequestRepositoryHandler implements JobRequestRepository {

  private final JobRequestRepositoryJpa jpa;
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
