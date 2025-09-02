package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record RequesterId(UUID value) {

  public RequesterId {
    if (value == null) {
      if (value == null) {
        throw new DomainViolation(
            "INCORRECT_REQUESTER_ID",
            "Incorrect Requester id",
            Map.of("value", "null")
        );
      }
    }
  }

  public static RequesterId newId() {
    return new RequesterId(UUID.randomUUID());
  }
}
