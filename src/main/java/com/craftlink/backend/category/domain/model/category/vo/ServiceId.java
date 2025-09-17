package com.craftlink.backend.category.domain.model.category.vo;


import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record ServiceId(UUID value) {

  public ServiceId {
    if (value == null) {
      throw new DomainViolation(
          "SERVICE_ID_NULL",
          "Service id cannot be null",
          Map.of("value", "null")
      );
    }
  }

  public static ServiceId newId() {
    return new ServiceId(UUID.randomUUID());
  }
}