package com.craftlink.backend.config.aws;

import com.craftlink.backend.config.aws.properties.AWSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@RequiredArgsConstructor
public class AWSConfiguration {

    private final AWSProperties AWSProperties;

    @Bean
    public S3Client s3Client() {
        var credentials = createCredentialsProvider();

        return S3Client.builder()
            .credentialsProvider(credentials)
            .region(Region.of(AWSProperties.getRegion()))
            .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        var credentials = createCredentialsProvider();

        return S3Presigner.builder()
            .credentialsProvider(credentials)
            .region(Region.of(AWSProperties.getRegion()))
            .build();
    }


    private AwsCredentialsProvider createCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(AWSProperties.getAccessKey(),
            AWSProperties.getSecretKey()));
    }
}
