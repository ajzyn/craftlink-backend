package com.craftlink.backend.auth.application.port.in.command.register;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;

public record RegisterUserCommand(String email, String password, String username, UserType userType) {

}
