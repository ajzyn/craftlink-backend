package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.time.LocalDate;
import java.util.Optional;

public record CalculatedDeadline(Optional<LocalDate> value) {

  public CalculatedDeadline {
    if (value.isPresent() && value.get().isBefore(LocalDate.now())) {
      throw new DomainViolation(
          "PAST_CALCULATED_DATE",
          "Calculated deadline value is in the past"
      );
    }
  }
}
