package com.craftlink.backend.auth.adapter.web.listener;

import com.craftlink.backend.auth.adapter.config.RefreshTokenCookieProperties;
import com.craftlink.backend.auth.application.TokenType;
import com.craftlink.backend.auth.domain.events.UserLoggedInEvent;
import com.craftlink.backend.shared.cookies.CookieOptions;
import com.craftlink.backend.shared.cookies.CookieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginEventListener {

  private final CookieService cookieService;
  private final RefreshTokenCookieProperties props;

  @EventListener
  public void onUserLoggedIn(UserLoggedInEvent event) {
    var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attrs == null || attrs.getResponse() == null) {
      log.warn("No HttpServletResponse bound to current thread; cannot set refresh cookie for user={}", event.userId());
      return;
    }

    var response = attrs.getResponse();
    cookieService.setCookie(response, CookieOptions.builder()
        .name(TokenType.REFRESH_TOKEN.name())
        .value(event.refreshToken())
        .httpOnly(props.isHttpOnly())
        .isSecure(props.isSecure())
        .path(props.getPath())
        .domain(props.getDomain())
        .expirationTimeInSeconds(props.getExpirationTimeInSeconds())
        .build());

    log.debug("Refresh token cookie set for user={}", event.userId());
  }
}