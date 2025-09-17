package com.craftlink.backend.infrastructure.exceptions.dtos;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

  private String error;
  private String message;
  private LocalDateTime timestamp;
  private Map<String, String> details;
}
