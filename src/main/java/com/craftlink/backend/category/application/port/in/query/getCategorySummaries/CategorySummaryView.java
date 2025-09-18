package com.craftlink.backend.category.application.port.in.query.getCategorySummaries;

import java.util.UUID;

public record CategorySummaryView(UUID id,
                                  String name,
                                  String slug,
                                  String iconName) {

}
