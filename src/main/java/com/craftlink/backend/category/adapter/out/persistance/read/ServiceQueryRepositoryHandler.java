package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import com.craftlink.backend.category.application.port.out.read.ServiceQueryRepository;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceQueryRepositoryHandler implements ServiceQueryRepository {

  private final ServiceQueryRepositoryJpa repository;

  @Override
  public Set<ServiceSummaryView> findActiveServiceSummariesByCategorySlug(String categorySlug) {
    return repository.findActiveServiceSummariesByCategorySlug(categorySlug);
  }

  @Override
  public Optional<ServiceDetailsView> findActiveServiceDetailsWithCategoryBySlug(String categorySlug) {
    return repository.findActiveServiceDetailsWithImageBySlug(categorySlug);
  }

  @Override
  public Set<ServiceSummaryView> findByNameContainingIgnoreCase(String searchPhrase) {
    return repository.findByNameContainingIgnoreCase(searchPhrase);
  }
}
