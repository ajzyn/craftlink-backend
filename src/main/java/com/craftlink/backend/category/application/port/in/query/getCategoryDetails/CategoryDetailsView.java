package com.craftlink.backend.category.application.port.in.query.getCategoryDetails;

import com.craftlink.backend.category.application.port.in.query.shared.ServiceSummaryView;
import java.util.Set;
import java.util.UUID;

public record CategoryDetailsView(UUID id,
                                  String name,
                                  String slug,
                                  String iconName,
                                  Set<ServiceSummaryView> services,
                                  String imageKey,
                                  String description) {

}
