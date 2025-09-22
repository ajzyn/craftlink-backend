package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record ServiceId(UUID value) {

  public ServiceId {
    if (value == null) {
      throw new DomainException(
          "INCORRECT_SERVICE_ID",
          "Incorrect service id",
          Map.of("value", "null")
      );
    }
  }
}