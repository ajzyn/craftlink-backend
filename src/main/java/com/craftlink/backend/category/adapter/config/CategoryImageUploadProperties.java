package com.craftlink.backend.category.adapter.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "app.s3.images.categories")
public class CategoryImageUploadProperties {

  private String prefix;
  private long maxSizeBytes;
  private List<String> allowedContentTypes;
  private List<String> allowedFileExtensions;
  private int cleanupOrphanedAfterHours;
}