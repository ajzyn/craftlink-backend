package com.craftlink.backend.auth.application.port.in.command.login;

import com.craftlink.backend.auth.application.port.in.command.shared.AuthResult;

public interface LoginUseCase {

  AuthResult handle(LoginCommand cmd);
}
