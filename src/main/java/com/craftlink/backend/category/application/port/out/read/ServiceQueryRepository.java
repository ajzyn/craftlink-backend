package com.craftlink.backend.category.application.port.out.read;

import com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.Optional;
import java.util.Set;

public interface ServiceQueryRepository {

  Optional<ServiceDetailsView> findActiveServiceDetailsWithCategoryBySlug(String categorySlug);

  Set<ServiceSummaryView> findByNameContainingIgnoreCase(String searchPhrase);

}
