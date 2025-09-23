package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;

public record YearsOfExperience(int value) {

  public YearsOfExperience {
    if (value < 0 || value > 80) {
      throw new DomainException("INVALID_YEARS_OF_EXPERIENCE",
          "Years of experience must be between 0 and 80",
          Map.of("value", String.valueOf(value)));
    }
  }

  public static YearsOfExperience none() {
    return new YearsOfExperience(0);
  }
}
