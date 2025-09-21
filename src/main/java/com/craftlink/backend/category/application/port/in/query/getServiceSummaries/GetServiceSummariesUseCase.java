package com.craftlink.backend.category.application.port.in.query.getServiceSummaries;

import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.List;

public interface GetServiceSummariesUseCase {

  List<ServiceSummaryView> handle(String searchPhrase);
}
