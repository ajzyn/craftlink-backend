package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.GetCategoryDetailsUseCase;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryDetailsHandler implements GetCategoryDetailsUseCase {

  private final CategoryQueryRepository categoryQueryRepository;

  @Override
  public CategoryDetailsView handle(String slug) {
    var category = categoryQueryRepository.findBySlug(slug).orElseThrow(() -> new BusinessException(
        ExceptionCode.RESOURCE_NOT_FOUND));

    return new CategoryDetailsView(
        category.id(),
        category.name(),
        category.slug(),
        category.iconName(),
        category.services(),
        category.imageKey(),
        category.description()
    );

  }
}
