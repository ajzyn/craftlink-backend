package com.craftlink.backend.auth.adapter.out.security.acl;

import com.craftlink.backend.auth.application.port.out.security.AccessControlService;
import com.craftlink.backend.auth.domain.model.security.vo.Permission;
import com.craftlink.backend.auth.domain.model.security.vo.ResourceId;
import com.craftlink.backend.shared.domain.vo.UserId;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//TODO: use database
@Component
@RequiredArgsConstructor
public class AccessControlServiceAdapter implements AccessControlService {

  private final Map<UUID, Map<UUID, Set<String>>> acl = new ConcurrentHashMap<>();

  @Override
  public boolean hasAccess(UserId userId, ResourceId resourceId, Permission permission) {
    return acl.getOrDefault(resourceId.value(), Map.of())
        .getOrDefault(userId.value(), Set.of())
        .contains(permission.value());
  }

  @Override
  public void grantPermission(UserId userId, ResourceId resourceId, Permission permission) {
    acl.computeIfAbsent(resourceId.value(), k -> new ConcurrentHashMap<>())
        .computeIfAbsent(userId.value(), k -> ConcurrentHashMap.newKeySet())
        .add(permission.value());
  }

  @Override
  public void revokePermission(UserId userId, ResourceId resourceId, Permission permission) {
    acl.getOrDefault(resourceId.value(), Map.of())
        .getOrDefault(userId.value(), Set.of())
        .remove(permission.value());
  }
}
