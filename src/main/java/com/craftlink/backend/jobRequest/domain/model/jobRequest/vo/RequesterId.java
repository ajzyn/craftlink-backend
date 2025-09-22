package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record RequesterId(UUID value) {

  public RequesterId {
    if (value == null) {
      throw new DomainException(
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
