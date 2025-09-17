package com.craftlink.backend.auth.adapter.out.security.authentication;

import com.craftlink.backend.auth.adapter.out.security.model.UserPrincipal;
import com.craftlink.backend.auth.application.port.out.security.AuthenticationService;
import com.craftlink.backend.auth.domain.model.security.vo.AuthenticationResult;
import com.craftlink.backend.auth.domain.model.security.vo.Credentials;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Spring Security adapter for the AuthenticationService.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationServiceAdapter implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  @Override
  public AuthenticationResult authenticate(Credentials credentials) {
    try {
      var auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password())
      );

      var principal = (UserPrincipal) auth.getPrincipal();

      return AuthenticationResult.success(
          principal.getId(),
          principal.getUsername(),
          principal.getUserType(),
          principal.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toSet())
      );
    } catch (AuthenticationException e) {
      return AuthenticationResult.failure();
    }
  }
}