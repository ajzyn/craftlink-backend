package com.craftlink.backend.service.domain.model;

import com.craftlink.backend.jobRequest.domain.model.vo.RequesterId;
import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record ServiceId(UUID value) {

  public ServiceId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_SERVICE_ID",
          "Incorrect Service id",
          Map.of("value", "null")
      );
    }
  }

  public static RequesterId newId() {
    return new RequesterId(UUID.randomUUID());
  }
}
