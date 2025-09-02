package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.time.LocalDate;

public record Deadline(LocalDate value) {

  public Deadline {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_CALCULATED_DATE",
          "Calculated deadline value is required"
      );
    }

    if (value().isBefore(LocalDate.now())) {
      throw new DomainViolation(
          "PAST_CALCULATED_DATE",
          "Calculated deadline value is in the past"
      );
    }
  }
}
