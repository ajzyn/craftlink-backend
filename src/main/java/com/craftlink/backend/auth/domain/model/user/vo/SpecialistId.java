package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record SpecialistId(UUID value) {

  public SpecialistId {
    if (value == null) {
      throw new DomainViolation("INCORRECT_SPECIALIST_ID",
          "Incorrect specialist id",
          Map.of("value", "null"));
    }
  }
}
