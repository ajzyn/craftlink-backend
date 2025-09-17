package com.craftlink.backend.shared.storage;

import java.time.Instant;

public record PresignedUrlDetails(
    String presignedUrl,
    String imageKey,
    Instant expiresAt) {

}
