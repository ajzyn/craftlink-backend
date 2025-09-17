package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.time.LocalDate;
import java.util.Optional;

public record Deadline(Optional<LocalDate> value) {

  private static final String ERROR_CODE = "PAST_DEADLINE_DATE";

  public Deadline {
    value.ifPresent(date -> {
      if (date.isBefore(LocalDate.now())) {
        throw new DomainViolation(
            ERROR_CODE,
            "Deadline value is in the past"
        );
      }
    });
  }

  public static Deadline of(LocalDate date, LocalDate referenceDate) {
    if (date.isBefore(referenceDate)) {
      throw new DomainViolation(ERROR_CODE, "Deadline cannot be in the past");
    }
    return new Deadline(Optional.of(date));
  }

  public static Deadline empty() {
    return new Deadline(Optional.empty());
  }
}