package com.craftlink.backend.category.application.port.out.read;

import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import java.util.List;
import java.util.Optional;

public interface CategoryQueryRepository {

  List<CategorySummaryView> findAllSummaries();

  Optional<CategoryDetailsView> findBySlug(String slug);
}
