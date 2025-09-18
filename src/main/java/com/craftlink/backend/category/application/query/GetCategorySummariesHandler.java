package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.CategorySummaryView;
import com.craftlink.backend.category.application.port.in.query.getCategorySummaries.GetCategorySummariesUseCase;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategorySummariesHandler implements GetCategorySummariesUseCase {

  private final CategoryQueryRepository categoryQueryRepository;

  @Override
  public List<CategorySummaryView> handle() {
    return categoryQueryRepository.findAllSummaries();
  }
}
