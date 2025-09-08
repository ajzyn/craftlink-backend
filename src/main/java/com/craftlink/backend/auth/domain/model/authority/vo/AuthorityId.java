package com.craftlink.backend.auth.domain.model.authority.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record AuthorityId(UUID value) {

  public AuthorityId {
    if (value == null) {
      throw new DomainViolation(
          "INCORRECT_AUTHORITY_ID",
          "Incorrect authority id",
          Map.of("value", "null")
      );
    }
  }

  public static AuthorityId newId() {
    return new AuthorityId(UUID.randomUUID());
  }
}
