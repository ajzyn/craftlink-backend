package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.CategoryDetailsView;
import com.craftlink.backend.category.application.port.in.query.getCategoryDetails.GetCategoryDetailsUseCase;
import com.craftlink.backend.category.application.port.out.read.CategoryQueryRepository;
import com.craftlink.backend.category.application.port.out.read.ServiceQueryRepository;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryDetailsHandler implements GetCategoryDetailsUseCase {

  private final ServiceQueryRepository serviceQueryRepository;
  private final CategoryQueryRepository categoryQueryRepository;

  @Override
  public CategoryDetailsView handle(String slug) {
    var category = categoryQueryRepository.findBySlug(slug).orElseThrow(() -> new BusinessException(
        ExceptionCode.RESOURCE_NOT_FOUND));

    var relatedServices = serviceQueryRepository.findActiveServicesByCategorySlug(slug);

    return new CategoryDetailsView(
        category.id(),
        category.name(),
        category.slug(),
        category.iconName(),
        new HashSet<>(relatedServices),
        category.imageKey(),
        category.description()
    );

  }
}
