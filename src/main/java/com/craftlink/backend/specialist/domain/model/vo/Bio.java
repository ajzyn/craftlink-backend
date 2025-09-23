package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;

public record Bio(String value) {

  public Bio {
    if (value != null && value.length() > 1000) {
      throw new DomainException("BIO_TOO_LONG",
          "Bio cannot exceed 1000 characters",
          Map.of("length", String.valueOf(value.length())));
    }
  }

  public static Bio empty() {
    return new Bio("");
  }
}
