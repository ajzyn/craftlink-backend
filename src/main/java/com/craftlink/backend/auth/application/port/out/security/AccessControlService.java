package com.craftlink.backend.auth.application.port.out.security;

import com.craftlink.backend.auth.domain.model.security.vo.Permission;
import com.craftlink.backend.auth.domain.model.security.vo.ResourceId;
import com.craftlink.backend.shared.vo.UserId;


public interface AccessControlService {


  boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission);

  void grantPermission(UserId userId, ResourceId resourceId, Permission permission);

  void revokePermission(UserId userId, ResourceId resourceId, Permission permission);
}