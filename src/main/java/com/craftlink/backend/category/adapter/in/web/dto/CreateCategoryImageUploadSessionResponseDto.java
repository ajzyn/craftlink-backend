package com.craftlink.backend.category.adapter.in.web.dto;

import java.time.Instant;

public record CreateCategoryImageUploadSessionResponseDto(String presignedUrl,
                                                          String imageKey,
                                                          Instant expiresAt) {

}
