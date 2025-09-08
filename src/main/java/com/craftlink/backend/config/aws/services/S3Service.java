package com.craftlink.backend.config.aws.services;

import com.craftlink.backend.config.aws.dtos.PresignedUploadRequestDto;
import com.craftlink.backend.config.aws.dtos.PresignedUploadResponseDto;
import com.craftlink.backend.config.aws.properties.AWSProperties;
import com.craftlink.backend.config.aws.properties.PresignedUrlArgs;
import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

  private final S3Client s3Client;
  private final S3Presigner s3Presigner;
  private final AWSProperties awsProperties;
  private final FileKeyGenerator keyGenerator;
  private final FileValidationService validationService;


  @Transactional
  public PresignedUploadResponseDto generatePresignedUploadUrl(PresignedUrlArgs presignedUrlArgs) {

    validationService.validateUploadRequest(presignedUrlArgs);

    String imageKey;

    if (presignedUrlArgs.getUserId() != null) {
      imageKey = keyGenerator.generateFileKey(presignedUrlArgs.getPrefix(),
          presignedUrlArgs.getRequest().getFileName(),
          presignedUrlArgs.getUserId());
    } else {
      imageKey = keyGenerator.generateFileKey(presignedUrlArgs.getPrefix(),
          presignedUrlArgs.getRequest().getFileName());
    }

    PutObjectRequest putObjectRequest = buildPutObjectRequest(imageKey,
        presignedUrlArgs.getRequest());
    PutObjectPresignRequest presignReq = buildPresignedRequest(putObjectRequest);

    try {
      URL presignedUrl = s3Presigner.presignPutObject(presignReq).url();
      Instant expiresAt = Instant.now()
          .plus(Duration.ofMinutes(awsProperties.getPresignedUrlTtlMinutes()));

      log.info("Presigned URL generated. Key: {}, expires at: {}", imageKey, expiresAt);

      return PresignedUploadResponseDto.builder()
          .presignedUrl(presignedUrl.toString())
          .imageKey(imageKey)
          .expiresAt(expiresAt)
          .maxFileSize(presignedUrlArgs.getMaxSize())
          .allowedTypes(String.join(", ", presignedUrlArgs.getAllowedContentTypes()))
          .build();

    } catch (Exception e) {
      log.error("Error generating presigned URL for key: {}", imageKey, e);
      throw new BusinessException(ExceptionCode.FAILED_GENERATE_S3_PRESIGNED_URL, e);
    }
  }


  public boolean imageExists(String fileKey) {
    try {
      s3Client.headObject(HeadObjectRequest.builder()
          .bucket(awsProperties.getBucketName())
          .key(fileKey)
          .build());

      log.debug("Image exists in S3: {}", fileKey);
      return true;

    } catch (NoSuchKeyException e) {
      log.debug("Image does not exist in S3: {}", fileKey);
      return false;

    } catch (Exception e) {
      log.error("Error verifying image existence: {}", fileKey, e);
      throw new BusinessException(ExceptionCode.FAILED_VERIFYING_IMAGE, e);
    }
  }

  public void deleteImage(String fileKey) {
    try {
      s3Client.deleteObject(DeleteObjectRequest.builder()
          .bucket(awsProperties.getBucketName())
          .key(fileKey)
          .build());

      log.info("Deleted image from S3: {}", fileKey);

    } catch (Exception e) {
      log.error("Error deleting image from S3: {}", fileKey, e);
      throw new BusinessException(ExceptionCode.FAILED_VERIFYING_IMAGE, e);
    }
  }

  public String generatePublicImageUrl(String fileKey) {
    return String.format("https://%s.s3.%s.amazonaws.com/%s",
        awsProperties.getBucketName(),
        awsProperties.getRegion(),
        fileKey);
  }

  private PutObjectRequest buildPutObjectRequest(String fileKey,
      PresignedUploadRequestDto request) {
    return PutObjectRequest.builder()
        .bucket(awsProperties.getBucketName())
        .key(fileKey)
        .contentType(request.getContentType())
        .contentLength(request.getFileSize())
        .metadata(Map.of(
            "original-filename", request.getFileName(),
            "upload-timestamp", String.valueOf(System.currentTimeMillis()),
            "file-size", request.getFileSize().toString(),
            "uploader", "category-manager-app"
        ))
        .build();
  }

  private PutObjectPresignRequest buildPresignedRequest(PutObjectRequest putObjectRequest) {
    return PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(awsProperties.getPresignedUrlTtlMinutes()))
        .putObjectRequest(putObjectRequest)
        .build();
  }
}
