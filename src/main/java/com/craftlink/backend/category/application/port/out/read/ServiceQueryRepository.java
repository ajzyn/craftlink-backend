package com.craftlink.backend.category.application.port.out.read;

import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.Set;

public interface ServiceQueryRepository {

  Set<ServiceSummaryView> findActiveServicesByCategorySlug(String categorySlug);
}
