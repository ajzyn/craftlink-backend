package com.craftlink.backend.auth.adapter.web.mapper;

import com.craftlink.backend.auth.adapter.web.dto.AuthResponseDto;
import com.craftlink.backend.auth.adapter.web.dto.LoginRequestDto;
import com.craftlink.backend.auth.adapter.web.dto.RegisterRequestDto;
import com.craftlink.backend.auth.application.dto.AuthResult;
import com.craftlink.backend.auth.application.dto.LoginCommand;
import com.craftlink.backend.auth.application.dto.RegisterUserCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

  RegisterUserCommand toCommand(RegisterRequestDto registerRequestDto);

  LoginCommand toCommand(LoginRequestDto loginRequestDto);

  AuthResponseDto toResponse(AuthResult authResult);
}
