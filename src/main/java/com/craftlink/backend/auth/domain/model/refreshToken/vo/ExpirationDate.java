package com.craftlink.backend.auth.domain.model.refreshToken.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.time.Instant;

public record ExpirationDate(Instant value) {

  public ExpirationDate {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_TOKEN_EXPIRATION_DATE",
          "Token expiration date required"
      );
    }

    if (value.isBefore(Instant.now())) {
      throw new DomainViolation(
          "PAST_TOKEN_EXPIRATION_DATE",
          "Token expiration date is in the past"
      );
    }
  }
}
