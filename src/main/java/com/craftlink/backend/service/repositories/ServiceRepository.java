package com.craftlink.backend.service.repositories;

import com.craftlink.backend.service.entities.ServiceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    List<ServiceEntity> findByNameContainingIgnoreCase(String searchPhrase);

    @Query("""
        SELECT DISTINCT s FROM ServiceEntity s
        LEFT JOIN FETCH s.category c
        LEFT JOIN FETCH c.image
        WHERE s.slug = :slug
        """)
    Optional<ServiceEntity> findBySlug(String slug);
}
