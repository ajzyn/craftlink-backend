package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.adapter.config.RefreshTokenCookieProperties;
import com.craftlink.backend.auth.application.dto.AuthResult;
import com.craftlink.backend.auth.application.dto.LoginCommand;
import com.craftlink.backend.auth.domain.events.UserLoggedInEvent;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.port.security.AccessTokenGenerator;
import com.craftlink.backend.auth.domain.port.security.Authenticator;
import com.craftlink.backend.auth.domain.port.security.SecureTokenGenerator;
import com.craftlink.backend.auth.domain.port.token.RefreshTokenRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginUseCaseImpl implements LoginUseCase {

  private final Authenticator authenticator;
  private final AccessTokenGenerator accessTokenGenerator;
  private final ApplicationEventPublisher eventPublisher;
  private final SecureTokenGenerator secureTokenGenerator;
  private final RefreshTokenCookieProperties refreshTokenCookieProperties;
  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  @Transactional
  public AuthResult handle(LoginCommand cmd) {
    var authenticated = authenticator.authenticate(cmd.email(), cmd.password());

    var accessToken = accessTokenGenerator.generateAccessToken(authenticated);

    var token = secureTokenGenerator.generateBase64Url(64);
    var refreshToken = RefreshToken.create(
        new UserId(authenticated.id()),
        token,
        new ExpirationDate(
            Instant.now().plus(refreshTokenCookieProperties.getExpirationTimeInSeconds(), ChronoUnit.SECONDS))
    );

    var savedRefreshToken = refreshTokenRepository.save(refreshToken);

    eventPublisher.publishEvent(
        new UserLoggedInEvent(authenticated.id(), authenticated.email(), savedRefreshToken.getToken().value(),
            Instant.now()));

    return new AuthResult(accessToken);
  }
}
