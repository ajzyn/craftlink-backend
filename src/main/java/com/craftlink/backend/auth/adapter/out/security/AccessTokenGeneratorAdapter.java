package com.craftlink.backend.auth.adapter.out.security;

import com.craftlink.backend.auth.application.port.in.query.getUserProfile.UserView;
import com.craftlink.backend.auth.application.port.out.security.AccessTokenGenerator;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenGeneratorAdapter implements AccessTokenGenerator {

  private final JwtTokenService jwtService;

  @Override
  public String generateAccessToken(UserView user) {
    return jwtService.generateAccessToken(
        user.id().toString(),
        user.email(),
        user.userType(),
        user.authorities().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet())
    );
  }
}
