package com.craftlink.backend.shared.dtos;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TemporaryCredentialsResponse {

    private String accessKeyId;
    private String secretAccessKey;
    private String sessionToken;
    private String region;
    private String bucketName;
    private long expiresInSeconds;
    private UploadPolicy uploadPolicy;

    @Getter
    @Setter
    @Builder
    public static class UploadPolicy {

        private String s3KeyPrefix;          // "categories/{categoryId}/"
        private long maxFileSize;           // Max bytes
        private Set<String> allowedContentTypes;
        private Set<String> allowedExtensions;
    }
}
