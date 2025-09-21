package com.craftlink.backend.category.application.port.in.query.getServiceDetails;

import java.util.UUID;

public record ServiceDetailsView(UUID id,
                                 String name,
                                 String slug,
                                 CategorySummaryWithImage category) {

}
