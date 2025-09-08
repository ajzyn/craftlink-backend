package com.craftlink.backend.shared.exceptions;

import java.util.Map;
import lombok.Getter;


//TODO: make it more secure
@Getter
public final class DomainViolation extends RuntimeException {

  private final String code;
  private final Map<String, Object> details;

  public DomainViolation(String code, String message) {
    this(code, message, Map.of());
  }

  public DomainViolation(String code, String message, Map<String, Object> details) {
    super(message);
    this.code = code;
    this.details = details;
  }

}
