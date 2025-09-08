package com.craftlink.backend.user.dtos;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private UUID id;
  private String username;
  private String authorities;
  private Set<String> offeredServices;
  private UserType userType;
}
