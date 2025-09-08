package com.craftlink.backend.auth.adapter.security;

import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.auth.domain.port.security.SecureTokenGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class SecureRandomTokenGeneratorAdapter implements SecureTokenGenerator {

  private static final SecureRandom RNG = new SecureRandom();

  @Override
  public RefreshTokenValue generateBase64Url(int bytes) {
    var buf = new byte[bytes];
    RNG.nextBytes(buf);
    return new RefreshTokenValue(Base64.getUrlEncoder().withoutPadding().encodeToString(buf));
  }
}
