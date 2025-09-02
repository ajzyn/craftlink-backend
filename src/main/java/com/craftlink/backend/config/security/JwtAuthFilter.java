package com.craftlink.backend.config.security;

import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.config.security.models.UserPrincipal;
import com.craftlink.backend.config.security.services.JwtService;
import com.craftlink.backend.shared.services.UuidUtils;
import com.craftlink.backend.user.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private static final int BEARER_PREFIX_LENGTH = 7;

  private final JwtService jwtService;
  private final UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain)
      throws ServletException, IOException {

    var authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")
        || SecurityContextHolder.getContext().getAuthentication() != null) {
      filterChain.doFilter(request, response);
      return;
    }

    var jwtToken = authHeader.substring(BEARER_PREFIX_LENGTH);
    var userId = jwtService.getUserId(jwtToken);

    if (userId != null) {
      var uuid = UuidUtils.safeParse(userId).orElseThrow(
          () -> new SecurityException(ExceptionCode.JWT_INVALID, "Invalid token subject: " + userId));

      var user = userService.getUserById(uuid).orElseThrow(
          () -> new SecurityException(ExceptionCode.AUTHENTICATION_FAILED, "User not found. ID: " + userId));

      var userPrincipal = new UserPrincipal(user);

      var authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    filterChain.doFilter(request, response);
  }
}
