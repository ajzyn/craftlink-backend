package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
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

  public static JobRequestId newId() {
    return new JobRequestId(UUID.randomUUID());
  }
}
