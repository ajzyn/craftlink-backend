package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryQueryRepositoryHandler implements CategoryQueryRepository {

  private final CategoryQueryRepositoryJpa repository;

  @Override
  public List<CategorySummaryView> findAllSummaries() {
    return repository.findAllSummaries();
  }

  @Override
  public Optional<CategoryDetailsView> findBySlug(String slug) {
    return repository.findBySlugWithImage(slug);
  }
}
