package com.craftlink.backend.infrastructure.storage.s3;

import com.craftlink.backend.infrastructure.config.AWSProperties;
import com.craftlink.backend.infrastructure.exceptions.custom.InfrastructureException;
import com.craftlink.backend.shared.storage.FileStoragePort;
import com.craftlink.backend.shared.storage.PresignedUrlDetails;
import com.craftlink.backend.shared.storage.PresignedUrlRequest;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3FileStorageAdapter implements FileStoragePort {

  private final S3Client s3Client;
  private final S3Presigner s3Presigner;
  private final AWSProperties awsProperties;

  @Override
  public PresignedUrlDetails generatePresignedUploadUrl(PresignedUrlRequest args) {

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(awsProperties.bucketName())
        .key(args.fileKey())
        .contentType(args.contentType())
        .contentLength(args.fileSize())
        .metadata(Map.of(
            "original-filename", args.fileName(),
            "upload-timestamp", String.valueOf(System.currentTimeMillis()),
            "file-size", Long.toString(args.fileSize()),
            "uploader", "craftlink-app"
        ))
        .build();

    PutObjectPresignRequest presignReq = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(awsProperties.presignedUrlTtlMinutes()))
        .putObjectRequest(putObjectRequest)
        .build();

    try {
      URL presignedUrl = s3Presigner.presignPutObject(presignReq).url();
      Instant expiresAt = Instant.now().plus(Duration.ofMinutes(awsProperties.presignedUrlTtlMinutes()));

      log.info("Generated presigned URL for key={}, expiresAt={}", args.fileKey(), expiresAt);

      return new PresignedUrlDetails(presignedUrl.toString(), args.fileKey(), expiresAt);
    } catch (S3Exception | SdkClientException e) {
      log.error("AWS error generating presigned URL for key={}", args.fileKey(), e);
      throw new InfrastructureException("Failed to generate presigned S3 URL", e);
    }
  }

  @Override
  public boolean exist(String fileKey) {
    try {
      s3Client.headObject(HeadObjectRequest.builder()
          .bucket(awsProperties.bucketName())
          .key(fileKey)
          .build());
      return true;
    } catch (NoSuchKeyException e) {
      return false;
    }
  }

  @Override
  public String generatePublicUrl(String fileKey) {
    return String.format("https://%s.s3.%s.amazonaws.com/%s",
        awsProperties.bucketName(),
        awsProperties.region(),
        fileKey);
  }

  @Override
  public void delete(String fileKey) {
    s3Client.deleteObject(DeleteObjectRequest.builder()
        .bucket(awsProperties.bucketName())
        .key(fileKey)
        .build());
  }
}
