package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getServiceDetails.GetServiceDetailsUseCase;
import com.craftlink.backend.category.application.port.in.query.getServiceDetails.ServiceDetailsView;
import com.craftlink.backend.category.application.port.out.read.ServiceQueryRepository;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetServiceDetailsHandler implements GetServiceDetailsUseCase {

  private final ServiceQueryRepository serviceQueryRepository;

  @Override
  public ServiceDetailsView handle(String slug) {
    return serviceQueryRepository.findActiveServiceDetailsWithCategoryBySlug(slug)
        .orElseThrow(() -> new BusinessException(
            ExceptionCode.RESOURCE_NOT_FOUND));
  }
}
