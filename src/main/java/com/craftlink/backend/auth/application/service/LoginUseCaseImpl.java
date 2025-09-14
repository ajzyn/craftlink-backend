package com.craftlink.backend.auth.application.service;

import com.craftlink.backend.auth.adapter.config.RefreshTokenCookieProperties;
import com.craftlink.backend.auth.application.dto.AuthResult;
import com.craftlink.backend.auth.application.dto.LoginCommand;
import com.craftlink.backend.auth.application.dto.UserView;
import com.craftlink.backend.auth.application.port.usecase.LoginUseCase;
import com.craftlink.backend.auth.domain.events.UserLoggedInEvent;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.security.vo.Credentials;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.port.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.port.security.AccessTokenGenerator;
import com.craftlink.backend.auth.domain.port.security.AuthenticationService;
import com.craftlink.backend.auth.domain.port.security.SecureTokenGenerator;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginUseCaseImpl implements LoginUseCase {

  private final AuthenticationService authenticationService;
  private final AccessTokenGenerator accessTokenGenerator;
  private final ApplicationEventPublisher eventPublisher;
  private final SecureTokenGenerator secureTokenGenerator;
  private final RefreshTokenCookieProperties refreshTokenCookieProperties;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  @Transactional
  public AuthResult handle(LoginCommand cmd) {
    var authResult = authenticationService.authenticate(Credentials.of(cmd.email(), cmd.password()));

    if (!authResult.authenticated()) {
      throw new SecurityException(ExceptionCode.AUTHENTICATION_FAILED);
    }

    var accessToken = accessTokenGenerator.generateAccessToken(new UserView(
        authResult.userId(),
        authResult.username(),
        authResult.userType(),
        authResult.authorities()
    ));

    var token = secureTokenGenerator.generateBase64Url(64);
    var refreshToken = RefreshToken.create(
        new UserId(authResult.userId()),
        token,
        new ExpirationDate(
            Instant.now().plus(refreshTokenCookieProperties.getExpirationTimeInSeconds(), ChronoUnit.SECONDS))
    );

    var savedRefreshToken = refreshTokenRepository.save(refreshToken);

    eventPublisher.publishEvent(
        new UserLoggedInEvent(authResult.userId(), authResult.username(), savedRefreshToken.getToken().value(),
            Instant.now()));

    return new AuthResult(accessToken);
  }
}
