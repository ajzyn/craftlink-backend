package com.craftlink.backend.auth.domain.model.security.vo;

import lombok.Getter;

/**
 * Value object representing a role in the system.
 */
@Getter
public final class Role {

  private final String value;

  private Role(String value) {
    this.value = value;
  }

  /**
   * Creates a new Role instance.
   *
   * @param value the role value
   * @return a new Role instance
   */
  public static Role of(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Role value cannot be null or blank");
    }
    return new Role(value);
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Role that = (Role) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value;
  }
}