package com.craftlink.backend.specialist.adapter.out.persistence.read;

import com.craftlink.backend.specialist.adapter.out.persistence.SpecialistEntity;
import com.craftlink.backend.specialist.application.port.in.query.shared.SpecialistProfileView;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecialistQueryRepositoryJpa extends JpaRepository<SpecialistEntity, UUID> {

  @Query("""
      SELECT new com.craftlink.backend.specialist.application.port.in.query.shared.SpecialistProfileView(
            s.id,
            s.fullName,
            s.phoneNumber,
            s.location,
            s.bio,
            s.profilePhotoUrl,
            s.yearsOfExperience,
            s.verified,
            s.offeredServices,
            s.type
            )
            FROM SpecialistEntity s
            WHERE s.id = :id
      """)
  Optional<SpecialistProfileView> findSpecialistProfileViewById(UUID id);
}
