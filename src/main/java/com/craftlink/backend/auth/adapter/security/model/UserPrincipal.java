package com.craftlink.backend.auth.adapter.security.model;

import com.craftlink.backend.auth.application.dto.UserAuthView;
import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.Collection;
import java.util.Collections;
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
  private final UUID clientId;
  private final UUID specialistId;
  private final String username;
  private final String password;
  private final UserType userType;
  private final Collection<? extends GrantedAuthority> authorities;
  private final Set<String> offeredServices;

  public UserPrincipal(UserAuthView view) {
    this.id = view.id();
    this.clientId = view.clientId();
    this.specialistId = view.specialistId();
    this.username = view.email();
    this.password = view.password();
    this.userType = view.userType();
    this.offeredServices = view.offeredServices();

    var baseAuthorities = view.authorities().stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toSet());

    baseAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userType.name()));

    this.authorities = Collections.unmodifiableSet(baseAuthorities);
  }

}
