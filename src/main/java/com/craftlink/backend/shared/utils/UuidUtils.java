package com.craftlink.backend.shared.utils;

import java.util.Optional;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UuidUtils {

  public Optional<UUID> safeParse(String input) {
    try {
      return Optional.of(UUID.fromString(input));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }
}
