package com.craftlink.backend.category.application.port.in.query.getServiceDetails;

import java.util.UUID;

public record CategorySummaryWithImage(UUID id,
                                       String name,
                                       String slug,
                                       String iconName,
                                       String imageKey) {

}
