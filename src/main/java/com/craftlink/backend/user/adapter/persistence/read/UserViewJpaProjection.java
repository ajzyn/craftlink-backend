package com.craftlink.backend.user.adapter.persistence.read;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.UUID;

public interface UserViewJpaProjection {

  UUID getId();

  String getEmail();

  String getAuthorities();

  UserType getUserType();
}
