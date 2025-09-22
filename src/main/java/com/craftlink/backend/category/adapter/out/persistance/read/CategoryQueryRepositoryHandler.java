package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    return repository.findBySlug(slug)
        .map(c -> new CategoryDetailsView(
            c.getId(),
            c.getName(),
            c.getSlug(),
            c.getIconName(),
            c.getServices().stream()
                .map(s -> new ServiceSummaryView(s.getId(), s.getName(), s.getSlug()))
                .collect(Collectors.toSet()),
            c.getImage().getImageKey(),
            c.getDescription()
        ));
  }
}
