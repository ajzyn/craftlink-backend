package com.craftlink.backend.specialist.adapter.out.persistence.write;

import com.craftlink.backend.specialist.adapter.out.persistence.SpecialistEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataSpecialistJpa extends JpaRepository<SpecialistEntity, UUID> {

}
