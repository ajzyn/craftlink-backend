package com.craftlink.backend.infrastructure.storage.s3;

import com.craftlink.backend.shared.storage.FileIdentityPort;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class FileKeyGeneratorAdapter implements FileIdentityPort {

  public String generateKey(String prefix, String originalFileName, String userId) {

    String timestamp = String.valueOf(System.currentTimeMillis());
    String randomSuffix = generateRandomSuffix();
    String sanitizedFileName = sanitizeFileName(originalFileName);

    return String.format("%s%s/%s_%s_%s",
        prefix,
        userId,
        timestamp,
        randomSuffix,
        sanitizedFileName);
  }

  public String generateKey(String prefix, String originalFileName) {

    String timestamp = String.valueOf(System.currentTimeMillis());
    String randomSuffix = generateRandomSuffix();
    String sanitizedFileName = sanitizeFileName(originalFileName);

    return String.format("%s/%s_%s_%s",
        prefix,
        timestamp,
        randomSuffix,
        sanitizedFileName);
  }


  private String generateRandomSuffix() {
    byte[] randomBytes = new byte[6];
    var secureRandom = new SecureRandom();
    secureRandom.nextBytes(randomBytes);

    return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(randomBytes);
  }


  private String sanitizeFileName(String fileName) {
    return fileName.toLowerCase()
        .replaceAll("[^a-z0-9._-]", "_")
        .replaceAll("_{2,}", "_")
        .replaceAll("^_+|_+$", "");
  }
}
