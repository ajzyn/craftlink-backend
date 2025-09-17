package com.craftlink.backend.auth.adapter.out.security.authorization;

import java.util.Arrays;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorizationChecker {

  boolean hasSpecialization(Set<String> offeredServices, String code) {
    return offeredServices.contains(code);
  }

  boolean hasAnySpecialization(Set<String> offeredServices, String... codes) {
    return Arrays.stream(codes).anyMatch(offeredServices::contains);
  }

  boolean hasRole(Set<String> authorities, String role) {
    return authorities.contains("ROLE_" + role);
  }

  boolean hasPermission(Set<String> authorities, String permission) {
    return authorities.contains(permission);
  }
}
