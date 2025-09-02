package com.craftlink.backend.jobRequest.adapter.persistence.read;

import com.craftlink.backend.jobRequest.adapter.persistence.JobRequestEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJobRequestDetailsJpa extends JpaRepository<JobRequestEntity, UUID> {

  @Query("""
        select sr.id as id,
               sr.status as status,
               sr.createdAt as createdAt,
               sr.requester.id as requesterId,
               sr.service.id as serviceId
        from JobRequestEntity sr
        where sr.id = :id
      """)
  Optional<JobRequestBasicViewProjection> findViewById(UUID id);
}
