package com.craftlink.backend.auth.domain.model.security.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public record ResourceId(UUID value) {

  public ResourceId {
    if (value == null) {
      throw new DomainException("RESOURCE_ID_INVALID", "Resource ID value cannot be null");
    }
  }

  public static ResourceId of(UUID value) {
    return new ResourceId(value);
  }

  public static ResourceId of(String value) {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("RESOURCE_ID_INVALID", "Resource ID value cannot be null");
    }
    try {
      return new ResourceId(UUID.fromString(value));
    } catch (IllegalArgumentException e) {
      throw new DomainException("RESOURCE_ID_INVALID", String.format("Invalid resource ID format %s", e));
    }
  }

}
