package com.craftlink.backend.jobRequest.adapter.port.out.persistence.write;

import com.craftlink.backend.jobRequest.adapter.port.out.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.domain.model.jobRequest.vo.JobRequestStatus;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRequestRepositoryJpa extends JpaRepository<JobRequestEntity, UUID> {

  boolean existsByRequesterIdAndStatusIn(UUID requesterId, Collection<JobRequestStatus> statuses);
}
