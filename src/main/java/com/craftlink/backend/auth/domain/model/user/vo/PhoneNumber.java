package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.regex.Pattern;

public record PhoneNumber(String value) {

  private static final Pattern P = Pattern.compile("^\\+?[1-9]\\d{7,14}$");

  public PhoneNumber {
    if (!P.matcher(value).matches()) {
      throw new DomainException("USER_PHONE_NUMBER_INVALID", "Invalid phone number");
    }
  }
}
