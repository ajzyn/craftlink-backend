package com.craftlink.backend.infrastructure.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "aws.s3")
@Validated
public record AWSProperties(
    @NotBlank String region,
    @NotNull String bucketName,
    @NotNull int presignedUrlTtlMinutes
) {

}