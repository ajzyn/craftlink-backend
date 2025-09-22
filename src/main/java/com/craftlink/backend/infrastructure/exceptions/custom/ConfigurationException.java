package com.craftlink.backend.infrastructure.exceptions.custom;

import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import lombok.Getter;

@Getter
public class ConfigurationException extends RuntimeException {

  private final ExceptionCode code;

  public ConfigurationException(ExceptionCode code, Throwable cause) {
    super(code.getUserMessage(), cause);
    this.code = code;
  }
}
