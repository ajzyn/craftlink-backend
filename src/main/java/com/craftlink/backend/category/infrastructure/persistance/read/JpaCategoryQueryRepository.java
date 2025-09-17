package com.craftlink.backend.category.infrastructure.persistance.read;

import com.craftlink.backend.category.infrastructure.persistance.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryQueryRepository extends JpaRepository<CategoryEntity, Integer> {

  @Query("""
      SELECT DISTINCT c FROM CategoryEntity c
      JOIN FETCH c.services
      LEFT JOIN FETCH c.image
      WHERE c.slug = :slug
      """)
  Optional<CategoryEntity> findBySlugWithServicesAndImage(String slug);
}
