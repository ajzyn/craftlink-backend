package com.craftlink.backend.auth.adapter.out.event;

import com.craftlink.backend.auth.domain.events.UserLoggedOutEvent;
import com.craftlink.backend.infrastructure.config.RefreshTokenCookieProperties;
import com.craftlink.backend.shared.cookies.CookieNames;
import com.craftlink.backend.shared.cookies.CookieOptions;
import com.craftlink.backend.shared.cookies.CookieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
@RequiredArgsConstructor
public class LogoutEventListener {

  private final CookieService cookieService;
  private final RefreshTokenCookieProperties props;


  @EventListener
  public void onUserLogout(UserLoggedOutEvent event) {
    var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attrs == null || attrs.getResponse() == null) {
      log.warn("No HttpServletResponse bound to current thread; cannot clear refresh cookie for user={}",
          event.userId());
      return;
    }

    var response = attrs.getResponse();
    cookieService.clearCookie(response, CookieNames.REFRESH_TOKEN, CookieOptions.builder()
        .httpOnly(props.isHttpOnly())
        .isSecure(props.isSecure())
        .path(props.getPath())
        .domain(props.getDomain())
        .sameSite(props.getSameSite())
        .build());

    log.debug("Refresh token cookie was cleared for user={}", event.userId());
  }

}
