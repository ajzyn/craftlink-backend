package com.craftlink.backend.category.application.port.in.query.getCategorySummaries;

import java.util.List;

public interface GetCategorySummariesUseCase {

  List<CategorySummaryView> handle();
}
