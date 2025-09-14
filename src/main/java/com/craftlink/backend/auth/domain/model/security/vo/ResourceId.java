package com.craftlink.backend.auth.domain.model.security.vo;

import java.util.UUID;
import lombok.Getter;

/**
 * Value object representing a resource ID in the system.
 */
@Getter
public final class ResourceId {

  private final UUID value;

  private ResourceId(UUID value) {
    this.value = value;
  }

  /**
   * Creates a new ResourceId instance.
   *
   * @param value the resource ID value
   * @return a new ResourceId instance
   */
  public static ResourceId of(UUID value) {
    if (value == null) {
      throw new IllegalArgumentException("Resource ID value cannot be null");
    }
    return new ResourceId(value);
  }

  /**
   * Creates a new ResourceId instance from a string.
   *
   * @param value the resource ID value as a string
   * @return a new ResourceId instance
   */
  public static ResourceId of(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Resource ID value cannot be null or blank");
    }
    try {
      return new ResourceId(UUID.fromString(value));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid resource ID format", e);
    }
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    ResourceId that = (ResourceId) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value.toString();
  }
}