package com.craftlink.backend.jobRequest.adapter.port.out.persistence.read;

import com.craftlink.backend.jobRequest.adapter.port.out.persistence.JobRequestEntity;
import com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.JobRequestDetailsView;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRequestQueryRepositoryJpa extends JpaRepository<JobRequestEntity, UUID> {

  @Query("""
        SELECT new com.craftlink.backend.jobRequest.application.port.in.query.getJobRequestDetails.JobRequestDetailsView(
              jr.id,
              jr.status,
              jr.createdAt,
              jr.requester.id,
              jr.service.id
              )
        FROM JobRequestEntity jr
        WHERE jr.id = :id
      """)
  Optional<JobRequestDetailsView> findDetailsById(UUID id);
}
