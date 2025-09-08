package com.craftlink.backend.auth.adapter.web;

import com.craftlink.backend.auth.adapter.service.RefreshTokenService;
import com.craftlink.backend.auth.adapter.web.dto.LoginRequestDto;
import com.craftlink.backend.auth.adapter.web.dto.LoginResponseDto;
import com.craftlink.backend.auth.adapter.web.dto.RegisterRequestDto;
import com.craftlink.backend.auth.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

  private final AuthService authService;
  private final RefreshTokenService refreshTokenService;

  @PostMapping("/register-client")
  public ResponseEntity<HttpStatus> registerClient(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    authService.registerClient(registerRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/register-specialist")
  public ResponseEntity<HttpStatus> registerSpecialist(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    authService.registerSpecialist(registerRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto,
      HttpServletResponse response) {
    return ResponseEntity.ok(authService.login(loginRequestDto, response));
  }

  @GetMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(HttpServletRequest request) {
    var token = refreshTokenService.refreshAccessToken(request);
    return ResponseEntity.ok(Map.of("token", token));
  }
}
