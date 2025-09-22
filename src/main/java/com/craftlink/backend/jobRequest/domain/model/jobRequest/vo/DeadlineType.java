package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.time.LocalDate;

public enum DeadlineType {

  ASAP,
  WITHIN_5_DAYS(5),
  WITHIN_2_WEEKS(14),
  EXACT_DATE,
  ADJUST;

  private final int daysToAdd;

  DeadlineType() {
    this.daysToAdd = 0;
  }

  DeadlineType(int daysToAdd) {
    this.daysToAdd = daysToAdd;
  }

  public Deadline calculate(LocalDate referenceDate) {
    return switch (this) {
      case WITHIN_5_DAYS, WITHIN_2_WEEKS -> Deadline.of(referenceDate.plusDays(daysToAdd), referenceDate);
      case ADJUST, ASAP -> Deadline.empty();
      case EXACT_DATE -> throw new DomainException(
          "EXACT_DATE_REQUIRES_FIELD",
          "Exact date must be provided when deadline type is EXACT_DATE"
      );
    };
  }

  public Deadline calculateExact(LocalDate exactDate, LocalDate referenceDate) {
    if (this != EXACT_DATE) {
      throw new DomainException(
          "INVALID_DEADLINE_TYPE",
          String.format("%s does not support exact date", this)
      );
    }
    return Deadline.of(exactDate, referenceDate);
  }
}
