package com.craftlink.backend.client.domain.model.client.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record ClientId(UUID value) {

  public ClientId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_JOB_REQUEST_ID",
          "Incorrect job request id",
          Map.of("value", "null")
      );
    }
  }

  public static ClientId newId() {
    return new ClientId(UUID.randomUUID());
  }
}
