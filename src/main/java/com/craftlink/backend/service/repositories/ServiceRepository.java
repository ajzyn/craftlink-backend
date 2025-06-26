package com.craftlink.backend.service.repositories;

import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    List<ServiceEntity> findByNameContainingIgnoreCase(String searchPhrase);
}
