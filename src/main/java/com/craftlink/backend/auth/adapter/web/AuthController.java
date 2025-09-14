package com.craftlink.backend.auth.adapter.web;

import com.craftlink.backend.auth.adapter.security.TokenType;
import com.craftlink.backend.auth.adapter.web.dto.AuthResponseDto;
import com.craftlink.backend.auth.adapter.web.dto.LoginRequestDto;
import com.craftlink.backend.auth.adapter.web.dto.RegisterRequestDto;
import com.craftlink.backend.auth.adapter.web.mapper.AuthWebMapper;
import com.craftlink.backend.auth.application.port.usecase.LoginUseCase;
import com.craftlink.backend.auth.application.port.usecase.RefreshTokenUseCase;
import com.craftlink.backend.auth.application.port.usecase.RegisterClientUserUseCase;
import com.craftlink.backend.shared.cookies.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

  private final LoginUseCase loginUseCase;
  private final RegisterClientUserUseCase registerClientUserUseCase;
  private final RefreshTokenUseCase refreshTokenUseCase;
  private final CookieService cookieService;
  private final AuthWebMapper mapper;

  @PostMapping("/register-client")
  public ResponseEntity<HttpStatus> registerClient(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    var cmd = mapper.toCommand(registerRequestDto);
    registerClientUserUseCase.handle(cmd);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

//  @PostMapping("/register-specialist")
//  public ResponseEntity<HttpStatus> registerSpecialist(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
//    return ResponseEntity.status(HttpStatus.CREATED).build();
//  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    var cmd = mapper.toCommand(loginRequestDto);
    var response = mapper.toResponse(loginUseCase.handle(cmd));
    return ResponseEntity.ok(response);
  }


  @GetMapping("/refresh-token")
  public ResponseEntity<AuthResponseDto> refreshToken(HttpServletRequest request) {
    var token = cookieService.getCookie(request, TokenType.REFRESH_TOKEN.name());
    var accessToken = refreshTokenUseCase.handle(token);
    var response = mapper.toResponse(accessToken);

    return ResponseEntity.ok().body(response);
  }
}
