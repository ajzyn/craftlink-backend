package com.craftlink.backend.category.application.port.out.write;

import com.craftlink.backend.category.infrastructure.persistance.ServiceEntity;
import com.craftlink.backend.shared.enums.LifecycleStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface ServiceRepository {

  //TODO: throw a buisness excpetion if exists
  @Query("""
      SELECT DISTINCT s FROM ServiceEntity s
      LEFT JOIN FETCH s.category c
      LEFT JOIN FETCH c.image
      WHERE s.slug = :slug AND s.status = :status
      """)
  Optional<ServiceEntity> findBySlug(String slug, LifecycleStatus status);
}
