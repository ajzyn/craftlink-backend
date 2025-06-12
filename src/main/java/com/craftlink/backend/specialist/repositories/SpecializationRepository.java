package com.craftlink.backend.specialist.repositories;

import com.craftlink.backend.specialist.entities.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Integer> {

    @Query("""
        SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END
        FROM SpecialistEntity sp
        JOIN sp.specializations s
        WHERE sp.user.id = :userId AND s.slug = :slug
        """)
    boolean existsByUserIdAndCode(Integer userId, String slug);
}
