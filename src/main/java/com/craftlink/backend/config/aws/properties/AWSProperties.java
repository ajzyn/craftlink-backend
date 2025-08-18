package com.craftlink.backend.config.aws.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "aws.s3")
@Validated
@Component
@Data
public class AWSProperties {

    @NotBlank
    private String region;

    @NotNull
    private String bucketName;

    @NotNull
    private int presignedUrlTtlMinutes;

}