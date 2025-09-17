package com.craftlink.backend.auth.adapter.out.security;

import com.craftlink.backend.auth.application.port.out.security.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordHasherAdapter implements PasswordHasher {

  private final PasswordEncoder passwordEncoder;

  @Override
  public String hash(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
}
