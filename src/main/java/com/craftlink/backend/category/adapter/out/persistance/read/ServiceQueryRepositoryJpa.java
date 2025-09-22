package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.adapter.out.persistance.ServiceEntity;
import com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceQueryRepositoryJpa extends JpaRepository<ServiceEntity, UUID> {

  @Query("""
        SELECT new com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView(
          s.id,
          s.name,
          s.slug
          )
            FROM ServiceEntity s
            WHERE s.category.slug = :categorySlug AND s.status = 'ACTIVE'
      """)
  Set<ServiceSummaryView> findActiveServiceSummariesByCategorySlug(String categorySlug);

  @Query("""
        SELECT new com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView(
          s.id,
          s.name,
          s.slug,
          new com.craftlink.backend.category.application.port.in.query.getServiceDetails.CategorySummaryWithImage(
              s.category.id,
              s.category.name,
              s.category.slug,
              s.category.iconName,
              s.category.image.imageKey
            )
          )
            FROM ServiceEntity s
            WHERE s.slug = :slug AND s.status = 'ACTIVE'
      """)
  Optional<ServiceDetailsView> findActiveServiceDetailsWithImageBySlug(String slug);

  Set<ServiceSummaryView> findByNameContainingIgnoreCase(String searchPhrase);
}
