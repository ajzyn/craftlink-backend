package com.craftlink.backend.auth.domain.model.security.vo;

import lombok.Getter;


@Getter
public final class Credentials {

  private final String username;
  private final String password;

  private Credentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Creates a new Credentials instance.
   *
   * @param username the username (email)
   * @param password the raw password
   * @return a new Credentials instance
   */
  public static Credentials of(String username, String password) {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username cannot be null or blank");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("Password cannot be null or blank");
    }
    return new Credentials(username, password);
  }
}