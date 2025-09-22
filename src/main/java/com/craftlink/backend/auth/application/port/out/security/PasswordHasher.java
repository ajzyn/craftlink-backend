package com.craftlink.backend.auth.application.port.out.security;

public interface PasswordHasher {

  String hash(String rawPassword);
}
