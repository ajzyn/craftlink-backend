package com.craftlink.backend.auth.domain.model.security.vo;

import lombok.Getter;

/**
 * Value object representing a permission in the system.
 */
@Getter
public final class Permission {

  private final String value;

  private Permission(String value) {
    this.value = value;
  }

  /**
   * Creates a new Permission instance.
   *
   * @param value the permission value
   * @return a new Permission instance
   */
  public static Permission of(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Permission value cannot be null or blank");
    }
    return new Permission(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Permission that = (Permission) o;
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