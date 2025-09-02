package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.time.LocalDate;

public record PreferredDate(LocalDate value) {

  public PreferredDate {
    if (value != null && value.isBefore(LocalDate.now())) {
      throw new DomainViolation(
          "PAST_PREFERRED_DATE",
          "Preferred value is in the past"
      );
    }
  }
}
