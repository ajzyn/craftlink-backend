package com.craftlink.backend.config.security;

import com.craftlink.backend.config.security.services.JwtService;
import com.craftlink.backend.config.security.services.UserDetailsServiceImpl;
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
  private final UserDetailsServiceImpl userDetailsService;

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
    var userEmail = jwtService.getUserEmail(jwtToken);

    if (userEmail != null) {
      var user = userDetailsService.loadUserByUsername(userEmail);

      var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    filterChain.doFilter(request, response);
  }
}
