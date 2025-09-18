package com.craftlink.backend.category.application.port.in.query.shared;

import java.util.UUID;

public record ServiceSummaryView(UUID id,
                                 String name,
                                 String slug) {

}
