package com.craftlink.backend.auth.adapter.security.config;

import com.craftlink.backend.config.exceptions.dtos.ErrorResponseDto;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    var error = ErrorResponseDto.builder()
        .message(ExceptionCode.UNAUTHORIZED.getUserMessage())
        .error(ExceptionCode.UNAUTHORIZED.getCode()).timestamp(LocalDateTime.now())
        .build();

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.getWriter().write(objectMapper.writeValueAsString(error));
  }
}
