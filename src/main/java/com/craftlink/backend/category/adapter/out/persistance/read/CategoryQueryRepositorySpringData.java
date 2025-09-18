package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.adapter.out.persistance.CategoryEntity;
import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryQueryRepositorySpringData extends JpaRepository<CategoryEntity, UUID> {

  @Query("""
      SELECT new com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView(
          c.id,
          c.name,
          c.slug,
          c.iconName,
          null,
          c.image.imageKey,
          c.description
           )
       FROM CategoryEntity c
       WHERE c.slug = :slug
      """)
  Optional<CategoryDetailsView> findBySlugWithImage(String slug);

  @Query("""
      SELECT new com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView(
            c.id, c.name, c.slug, c.iconName
            )
            FROM CategoryEntity c
      """)
  List<CategorySummaryView> findAllSummaries();


}
