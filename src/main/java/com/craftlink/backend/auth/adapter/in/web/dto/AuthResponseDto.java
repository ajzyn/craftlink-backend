package com.craftlink.backend.auth.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponseDto {

  private String token;
}
