package com.craftlink.backend.auth.domain.port.security;

public interface PasswordHasher {

  String hash(String rawPassword);
}
