package com.craftlink.backend.shared.services;

import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UuidUtils {

  public static Optional<UUID> safeParse(String input) {
    try {
      return Optional.of(UUID.fromString(input));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }
}
