package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_USER_ID",
          "Incorrect user id",
          Map.of("value", "null")
      );
    }
  }
}