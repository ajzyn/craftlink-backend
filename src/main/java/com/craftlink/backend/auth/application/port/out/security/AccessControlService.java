package com.craftlink.backend.auth.application.port.out.security;

import com.craftlink.backend.shared.domain.vo.Permission;
import com.craftlink.backend.shared.domain.vo.ResourceId;
import com.craftlink.backend.shared.domain.vo.UserId;


public interface AccessControlService {


  boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission);

  void grantPermission(UserId userId, ResourceId resourceId, Permission permission);

  void revokePermission(UserId userId, ResourceId resourceId, Permission permission);
}