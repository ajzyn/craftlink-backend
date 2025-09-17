package com.craftlink.backend.auth.application.command;

import com.craftlink.backend.auth.application.port.in.command.login.LoginCommand;
import com.craftlink.backend.auth.application.port.in.command.login.LoginUseCase;
import com.craftlink.backend.auth.application.port.in.command.shared.AuthResult;
import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserView;
import com.craftlink.backend.auth.application.port.out.security.AccessTokenGenerator;
import com.craftlink.backend.auth.application.port.out.security.AuthenticationService;
import com.craftlink.backend.auth.application.port.out.security.RefreshTokenGenerator;
import com.craftlink.backend.auth.application.port.out.write.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.events.UserLoggedInEvent;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.security.vo.Credentials;
import com.craftlink.backend.infrastructure.config.RefreshTokenCookieProperties;
import com.craftlink.backend.infrastructure.exceptions.custom.SecurityException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.vo.UserId;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginUseCaseHandler implements LoginUseCase {

  private final AuthenticationService authenticationService;
  private final AccessTokenGenerator accessTokenGenerator;
  private final ApplicationEventPublisher eventPublisher;
  private final RefreshTokenGenerator refreshTokenGenerator;
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

    var token = refreshTokenGenerator.generateBase64Url(64);
    var refreshToken = RefreshToken.create(
        new UserId(authResult.userId()),
        token,
        new ExpirationDate(
            Instant.now().plus(refreshTokenCookieProperties.getExpirationSeconds(), ChronoUnit.SECONDS))
    );

    var savedRefreshToken = refreshTokenRepository.save(refreshToken);

    eventPublisher.publishEvent(
        new UserLoggedInEvent(authResult.userId(), authResult.username(), savedRefreshToken.getToken().value(),
            Instant.now()));

    return new AuthResult(accessToken);
  }
}
