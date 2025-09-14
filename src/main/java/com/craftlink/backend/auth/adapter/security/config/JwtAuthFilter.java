package com.craftlink.backend.auth.adapter.security.config;

import com.craftlink.backend.auth.adapter.security.JwtTokenService;
import com.craftlink.backend.auth.adapter.security.model.UserPrincipal;
import com.craftlink.backend.auth.application.port.repository.UserQueryRepository;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.utils.UuidUtils;
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

  private final JwtTokenService jwtTokenService;
  private final UserQueryRepository userQueryRepository;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;


  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain)
      throws ServletException, IOException {

    try {
      var authHeader = request.getHeader("Authorization");

      if (authHeader == null || !authHeader.startsWith("Bearer ")
          || SecurityContextHolder.getContext().getAuthentication() != null) {
        filterChain.doFilter(request, response);
        return;
      }

      var jwtToken = authHeader.substring(BEARER_PREFIX_LENGTH);
      var userId = jwtTokenService.getUserId(jwtToken);

      if (userId != null) {
        var uuid = UuidUtils.safeParse(userId).orElseThrow(
            () -> new SecurityException(ExceptionCode.JWT_INVALID, "Invalid token subject: " + userId));

        var userSnapshot = userQueryRepository.findByIdWithServices(new UserId(uuid)).orElseThrow(
            () -> new SecurityException(ExceptionCode.AUTHENTICATION_FAILED, "User not found. ID: " + userId));

        var userPrincipal = new UserPrincipal(userSnapshot);

        var authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }

      filterChain.doFilter(request, response);
    } catch (SecurityException ex) {
      authenticationEntryPoint.commence(request, response,
          new org.springframework.security.core.AuthenticationException(ex.getMessage(), ex) {
          });
    }
  }
}
