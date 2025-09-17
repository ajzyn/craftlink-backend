package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record ServiceId(UUID value) {

  public ServiceId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_SERVICE_ID",
          "Incorrect service id",
          Map.of("value", "null")
      );
    }
  }
}