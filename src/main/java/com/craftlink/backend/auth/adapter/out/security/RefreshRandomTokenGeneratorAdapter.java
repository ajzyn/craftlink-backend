package com.craftlink.backend.auth.adapter.out.security;

import com.craftlink.backend.auth.application.port.out.security.RefreshTokenGenerator;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class RefreshRandomTokenGeneratorAdapter implements RefreshTokenGenerator {

  private static final SecureRandom RNG = new SecureRandom();

  @Override
  public RefreshTokenValue generateBase64Url(int bytes) {
    var buf = new byte[bytes];
    RNG.nextBytes(buf);
    return new RefreshTokenValue(Base64.getUrlEncoder().withoutPadding().encodeToString(buf));
  }
}
