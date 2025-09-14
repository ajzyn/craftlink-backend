package com.craftlink.backend.auth.domain.port.security;

import com.craftlink.backend.auth.domain.model.security.vo.Permission;
import com.craftlink.backend.auth.domain.model.security.vo.ResourceId;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;


public interface AccessControlService {


  boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission);

  void grantPermission(UserId userId, ResourceId resourceId, Permission permission);

  void revokePermission(UserId userId, ResourceId resourceId, Permission permission);
}