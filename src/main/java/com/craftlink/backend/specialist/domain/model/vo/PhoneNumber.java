package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.regex.Pattern;

public record PhoneNumber(String value) {


  private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+ ]{6,20}$");

  public PhoneNumber {
    if (value == null || !PHONE_PATTERN.matcher(value).matches()) {
      throw new DomainException("INCORRECT_PHONE_NUMBER",
          "Phone number format is invalid",
          Map.of("value", value == null ? "null" : value));
    }
  }
}
