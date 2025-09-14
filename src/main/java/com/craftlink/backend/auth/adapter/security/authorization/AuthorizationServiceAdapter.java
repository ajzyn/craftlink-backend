package com.craftlink.backend.auth.adapter.security.authorization;

import com.craftlink.backend.auth.application.port.repository.UserQueryRepository;
import com.craftlink.backend.auth.domain.model.security.vo.Permission;
import com.craftlink.backend.auth.domain.model.security.vo.ResourceId;
import com.craftlink.backend.auth.domain.model.security.vo.Role;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.port.security.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/*
  Purpose: Implementation of domain port for programmatic authorization checks
 */


@Component
@RequiredArgsConstructor
public class AuthorizationServiceAdapter implements AuthorizationService {

  private final UserQueryRepository userQueryRepository;


  @Override
  public boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission) {
    return hasPermission(userId, permission);
  }

  @Override
  public boolean hasRole(UserId userId, Role role) {
    return userQueryRepository.findByIdWithServices(userId)
        .map(user -> AuthorizationChecker.hasRole(user.authorities(), role.getValue()))
        .orElse(false);
  }

  @Override
  public boolean hasPermission(UserId userId, Permission permission) {
    return userQueryRepository.findByIdWithServices(userId)
        .map(user -> AuthorizationChecker.hasPermission(user.authorities(), permission.getValue()))
        .orElse(false);
  }

  @Override
  public boolean hasSpecialization(UserId userId, String specializationCode) {
    return userQueryRepository.findByIdWithServices(userId)
        .map(user -> AuthorizationChecker.hasSpecialization(user.offeredServices(), specializationCode))
        .orElse(false);
  }

  @Override
  public boolean hasAnySpecialization(UserId userId, String... specializationCodes) {
    return userQueryRepository.findByIdWithServices(userId)
        .map(user -> AuthorizationChecker.hasAnySpecialization(user.offeredServices(), specializationCodes))
        .orElse(false);
  }
}