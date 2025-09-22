package com.craftlink.backend.category.adapter.in.web.dto;

import java.time.Instant;
import java.util.UUID;

public record CreateCategoryImageUploadSessionResponseDto(
    UUID id,
    String presignedUrl,
    String imageKey,
    Instant expiresAt) {

}
