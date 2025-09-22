package com.craftlink.backend.infrastructure.exceptions.custom;

import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import java.util.Map;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

  private final ExceptionCode code;
  private final Map<String, String> details;

  public BusinessException(ExceptionCode code) {
    this(code, Map.of());
  }

  public BusinessException(ExceptionCode code, Map<String, String> details) {
    super(code.getUserMessage());
    this.code = code;
    this.details = details;
  }

  public BusinessException(ExceptionCode code, Throwable cause) {
    this(code, Map.of(), cause);
  }

  public BusinessException(ExceptionCode code, Map<String, String> details, Throwable cause) {
    super(code.getUserMessage(), cause);
    this.code = code;
    this.details = details;
  }
}
