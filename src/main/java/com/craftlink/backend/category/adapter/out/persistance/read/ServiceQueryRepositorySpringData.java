package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceQueryRepositorySpringData extends JpaRepository<ServiceEntity, UUID> {

  @Query("""
        SELECT new com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView(
          s.id,
          s.name,
          s.slug
          )
            FROM ServiceEntity s
            WHERE s.category.slug = :categorySlug AND s.status = 'ACTIVE'
      """)
  Set<ServiceSummaryView> findActiveServicesByCategorySlug(String categorySlug);
}
