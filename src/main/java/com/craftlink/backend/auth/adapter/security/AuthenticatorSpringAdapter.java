package com.craftlink.backend.auth.adapter.security;

import com.craftlink.backend.auth.adapter.config.models.UserPrincipal;
import com.craftlink.backend.auth.application.dto.UserSnapshot;
import com.craftlink.backend.auth.domain.port.security.Authenticator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticatorSpringAdapter implements Authenticator {

  private final AuthenticationManager authenticationManager;

  @Override
  public UserSnapshot authenticate(String email, String rawPassword) {
    var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, rawPassword));

    var principal = (UserPrincipal) auth.getPrincipal();

    return new UserSnapshot(
        principal.getId(),
        principal.getUsername(),
        principal.getUserType(),
        principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())
    );
  }
}
