package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.regex.Pattern;

public record Email(String value) {

  private static final Pattern P = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

  public Email {
    if (!P.matcher(value).matches()) {
      throw new DomainException("USER_EMAIL_INVALID", "Invalid email");
    }
  }
}
