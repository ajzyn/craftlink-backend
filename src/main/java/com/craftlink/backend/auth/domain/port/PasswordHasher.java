package com.craftlink.backend.auth.domain.port;

public interface PasswordHasher {

  String hash(String rawPassword);
}
