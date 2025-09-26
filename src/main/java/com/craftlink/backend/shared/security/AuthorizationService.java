package com.craftlink.backend.shared.security;

import com.craftlink.backend.shared.domain.vo.Permission;
import com.craftlink.backend.shared.domain.vo.ResourceId;
import com.craftlink.backend.shared.domain.vo.Role;
import com.craftlink.backend.shared.domain.vo.UserId;


public interface AuthorizationService {

  boolean hasPermission(UserId userId, Permission permission);

  boolean hasRole(UserId userId, Role role);

  boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission);

  boolean hasSpecialization(UserId userId, String specializationCode);

  boolean hasAnySpecialization(UserId userId, String... specializationCodes);
}