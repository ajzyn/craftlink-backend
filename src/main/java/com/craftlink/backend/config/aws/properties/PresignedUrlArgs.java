package com.craftlink.backend.config.aws.properties;

import com.craftlink.backend.config.aws.dtos.PresignedUploadRequestDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PresignedUrlArgs {

    private PresignedUploadRequestDto request;
    private Long maxSize;
    private List<String> allowedContentTypes;
    private List<String> allowedFileExtensions;
    private String prefix;
    private String userId;
}
