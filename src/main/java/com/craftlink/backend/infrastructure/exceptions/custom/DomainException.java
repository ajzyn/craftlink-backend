package com.craftlink.backend.infrastructure.exceptions.custom;

import java.util.Map;
import lombok.Getter;


@Getter
public final class DomainException extends RuntimeException {

  private final String code;
  private final Map<String, Object> details;

  public DomainException(String code, String message) {
    this(code, message, Map.of());
  }

  public DomainException(String code, String message, Map<String, Object> details) {
    super(message);
    this.code = code;
    this.details = details;
  }

}
