package com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession;

import java.time.Instant;

public record CreateCategoryImageUploadSessionResult(
    String presignedUrl,
    String imageKey,
    Instant expiresAt) {

}
