package com.craftlink.backend.jobRequest.adapter.persistence.write;

import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.domain.model.vo.JobRequestStatus;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJobRequestJpa extends JpaRepository<JobRequestEntity, UUID> {

  boolean existsByRequesterIdAndStatusIn(UUID requesterId, Collection<JobRequestStatus> statuses);
}
