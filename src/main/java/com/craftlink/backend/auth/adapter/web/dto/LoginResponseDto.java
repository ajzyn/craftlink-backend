package com.craftlink.backend.auth.adapter.web.dto;

import com.craftlink.backend.user.dtos.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {

  private UserDto user;
  private String token;
}
