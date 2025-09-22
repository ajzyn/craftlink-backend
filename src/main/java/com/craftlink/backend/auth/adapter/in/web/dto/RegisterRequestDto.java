package com.craftlink.backend.auth.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String username;

  @NotBlank
  @Pattern(regexp = "^.{8,}$",
      message = "Password must be at least 8 characters long")
  private String password;
}
