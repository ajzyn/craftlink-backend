package com.craftlink.backend.category.application.query;

import com.craftlink.backend.category.application.port.in.query.getServiceSummaries.GetServiceSummariesUseCase;
import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import com.craftlink.backend.category.application.port.out.read.ServiceQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetServiceSummariesHandler implements GetServiceSummariesUseCase {

  private final ServiceQueryRepository serviceQueryRepository;

  @Override
  public List<ServiceSummaryView> handle(String searchPhrase) {
    return serviceQueryRepository.findByNameContainingIgnoreCase(searchPhrase).stream().toList();
  }
}
