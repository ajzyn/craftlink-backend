package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.GetCategorySummariesUseCase;
import java.util.List;

public class GetCategorySummariesHandler implements GetCategorySummariesUseCase {

  @Override
  public List<CategorySummaryView> handle() {
    return List.of();
  }
}
