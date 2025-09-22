package com.craftlink.backend.client.domain.model.client.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record JobRequestId(UUID value) {

  public JobRequestId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_JOB_REQUEST_ID",
          "Incorrect job request id",
          Map.of("value", "null")
      );
    }
  }
}
