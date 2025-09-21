package com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession;

import java.time.Instant;
import java.util.UUID;

public record CreateCategoryImageUploadSessionResult(
    UUID id,
    String presignedUrl,
    String imageKey,
    Instant expiresAt) {

}
