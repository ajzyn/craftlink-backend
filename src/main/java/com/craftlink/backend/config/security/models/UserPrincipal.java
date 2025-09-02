package com.craftlink.backend.config.security.models;

import com.craftlink.backend.service.entities.ServiceEntity;
import com.craftlink.backend.user.entities.UserEntity;
import com.craftlink.backend.user.models.UserType;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserPrincipal implements UserDetails {

  private final UUID id;
  private final String username;
  private final String password;
  private final UserType userType;
  private final Collection<? extends GrantedAuthority> authorities;
  private final Set<String> offeredServices;

  public UserPrincipal(UserEntity user) {
    id = user.getId();
    username = user.getEmail();
    password = user.getPassword();
    userType = user.getUserType();
    authorities = user.getAuthorities()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getCode()))
        .collect(Collectors.toSet());

    if (user.getSpecialist() != null && user.getSpecialist().getOfferedServices() != null) {
      offeredServices = user.getSpecialist()
          .getOfferedServices()
          .stream()
          .map(ServiceEntity::getSlug)
          .collect(Collectors.toSet());
    } else {
      offeredServices = Set.of();
    }
  }
}
