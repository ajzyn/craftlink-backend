package com.craftlink.backend.auth.application.port.in.command.login;

import com.craftlink.backend.auth.application.port.in.command.shared.LoginResult;

public interface LoginUseCase {

  LoginResult handle(LoginCommand cmd);
}
