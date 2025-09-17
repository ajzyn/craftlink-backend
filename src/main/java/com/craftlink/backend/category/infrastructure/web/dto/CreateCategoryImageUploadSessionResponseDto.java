package com.craftlink.backend.category.infrastructure.web.dto;

import java.time.Instant;

public record CreateCategoryImageUploadSessionResponseDto(String presignedUrl,
                                                          String imageKey,
                                                          Instant expiresAt) {

}
