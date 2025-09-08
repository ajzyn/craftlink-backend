package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.time.LocalDate;

public record ExactDate(LocalDate value) {

  public ExactDate {
    if (value != null && value.isBefore(LocalDate.now())) {
      throw new DomainViolation(
          "PAST_EXACT_DATE",
          "Exact value is in the past"
      );
    }
  }
}
