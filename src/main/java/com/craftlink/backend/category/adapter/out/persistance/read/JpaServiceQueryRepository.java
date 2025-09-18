package com.craftlink.backend.category.adapter.out.persistance.read;

import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import com.craftlink.backend.category.application.port.out.read.ServiceQueryRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaServiceQueryRepository implements ServiceQueryRepository {

  private final ServiceQueryRepositorySpringData repository;

  @Override
  public Set<ServiceSummaryView> findActiveServicesByCategorySlug(String categorySlug) {
    return repository.findActiveServicesByCategorySlug(categorySlug);
  }
}
