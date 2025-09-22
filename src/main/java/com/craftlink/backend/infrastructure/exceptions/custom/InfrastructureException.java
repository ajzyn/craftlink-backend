package com.craftlink.backend.infrastructure.exceptions.custom;

import java.util.Map;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException {

  private final Map<String, String> details;

  public InfrastructureException(String message) {
    this(message, Map.of());
  }

  public InfrastructureException(String message, Map<String, String> details) {
    super(message);
    this.details = details;
  }

  public InfrastructureException(String message, Throwable cause) {
    this(message, Map.of(), cause);
  }

  public InfrastructureException(Throwable cause) {
    this(cause.getMessage(), Map.of(), cause);
  }

  public InfrastructureException(String message, Map<String, String> details, Throwable cause) {
    super(message, cause);
    this.details = details;
  }
}
