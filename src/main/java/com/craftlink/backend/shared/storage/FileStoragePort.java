package com.craftlink.backend.shared.storage;


public interface FileStoragePort {

  PresignedUrlDetails generatePresignedUploadUrl(PresignedUrlRequest args);

  String generatePublicUrl(String fileKey);

  boolean exist(String fileKey);

  void delete(String fileKey);
}
