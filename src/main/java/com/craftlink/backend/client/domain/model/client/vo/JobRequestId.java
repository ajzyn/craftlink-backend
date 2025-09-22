package com.craftlink.backend.client.domain.model.client.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record JobRequestId(UUID value) {

  public JobRequestId {
    if (value == null) {
      throw new DomainException(
          "INCORRECT_JOB_REQUEST_ID",
          "Incorrect job request id",
          Map.of("value", "null")
      );
    }
  }
}
