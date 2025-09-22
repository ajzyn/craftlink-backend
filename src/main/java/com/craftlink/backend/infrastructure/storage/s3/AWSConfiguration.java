package com.craftlink.backend.infrastructure.storage.s3;

import com.craftlink.backend.infrastructure.config.AWSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class AWSConfiguration {

  private final AWSProperties properties;

  @Bean
  public S3Client s3Client() {
    AwsCredentialsProvider credentials = EnvironmentVariableCredentialsProvider.create();
    return S3Client.builder()
        .credentialsProvider(credentials)
        .region(Region.of(properties.region()))
        .build();
  }

  @Bean
  public S3Presigner s3Presigner() {
    AwsCredentialsProvider credentials = EnvironmentVariableCredentialsProvider.create();
    return S3Presigner.builder()
        .credentialsProvider(credentials)
        .region(Region.of(properties.region()))
        .build();
  }
}
