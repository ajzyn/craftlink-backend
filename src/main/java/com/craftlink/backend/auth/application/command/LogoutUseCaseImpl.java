package com.craftlink.backend.auth.application.command;

import com.craftlink.backend.auth.application.port.in.command.logout.LogoutCommand;
import com.craftlink.backend.auth.application.port.in.command.logout.LogoutUseCase;
import com.craftlink.backend.auth.application.port.out.write.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.events.UserLoggedOutEvent;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LogoutUseCaseImpl implements LogoutUseCase {

  private final RefreshTokenRepository refreshTokenRepository;
  private final ApplicationEventPublisher eventPublisher;
  private final CurrentUserProvider currentUserProvider;


  @Override
  @Transactional
  public void handle(LogoutCommand cmd) {
    refreshTokenRepository.deleteByToken(new RefreshTokenValue(cmd.rawToken()));

    eventPublisher.publishEvent(new UserLoggedOutEvent(cmd.rawToken(), currentUserProvider.getCurrentUser().userId()));
  }
}
