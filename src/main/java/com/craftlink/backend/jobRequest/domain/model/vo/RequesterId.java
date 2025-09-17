package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record RequesterId(UUID value) {

  public RequesterId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_REQUESTER_ID",
          "Incorrect Requester id",
          Map.of("value", "null")
      );
    }
  }

  public static RequesterId newId() {
    return new RequesterId(UUID.randomUUID());
  }
}
