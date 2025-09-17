package com.craftlink.backend.shared.storage;

import lombok.Builder;

@Builder
public record PresignedUrlRequest(String fileKey,
                                  String contentType,
                                  long fileSize,
                                  String fileName) {

}
