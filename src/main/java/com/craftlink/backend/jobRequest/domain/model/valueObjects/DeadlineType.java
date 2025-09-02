package com.craftlink.backend.jobRequest.domain.model.valueObjects;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DeadlineType {

  ASAP(false),
  WITHIN_5_DAYS(true) {
    @Override
    public CalculatedDeadline calculate(Deadline baseDate) {
      return new CalculatedDeadline(Optional.of(baseDate.value().plusDays(5)));
    }
  },
  WITHIN_2_WEEKS(true) {
    @Override
    public CalculatedDeadline calculate(Deadline baseDate) {
      return new CalculatedDeadline(Optional.of(baseDate.value().plusDays(14)));
    }
  },
  EXACT_DATE(true) {
    @Override
    public CalculatedDeadline calculate(Deadline baseDate) {
      return new CalculatedDeadline(Optional.of(baseDate.value()));
    }
  },
  ADJUST(false);

  private final boolean supportsCalculation;

  public CalculatedDeadline calculate(Deadline baseDate) {
    throw new DomainViolation(
        "CALCULATION_NOT_SUPPORTED",
        String.format("%s does not support calculation", this.name())
    );
  }
}
