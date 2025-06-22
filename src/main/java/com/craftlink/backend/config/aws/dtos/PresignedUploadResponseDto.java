package com.craftlink.backend.config.aws.dtos;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PresignedUploadResponseDto {

    private String presignedUrl;

    private String imageKey;

    private Instant expiresAt;

    private Long maxFileSize;

    private String allowedTypes;
}
