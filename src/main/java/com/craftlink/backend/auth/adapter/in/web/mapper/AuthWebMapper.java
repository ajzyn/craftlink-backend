package com.craftlink.backend.auth.adapter.in.web.mapper;

import com.craftlink.backend.auth.adapter.in.web.dto.AuthResponseDto;
import com.craftlink.backend.auth.adapter.in.web.dto.LoginRequestDto;
import com.craftlink.backend.auth.adapter.in.web.dto.RegisterRequestDto;
import com.craftlink.backend.auth.application.port.in.command.login.LoginCommand;
import com.craftlink.backend.auth.application.port.in.command.register.RegisterUserCommand;
import com.craftlink.backend.auth.application.port.in.command.shared.AuthResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

  RegisterUserCommand toCommand(RegisterRequestDto registerRequestDto);

  LoginCommand toCommand(LoginRequestDto loginRequestDto);

  AuthResponseDto toResponse(AuthResult authResult);
}
