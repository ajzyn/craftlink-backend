package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.application.dto.AuthResult;
import com.craftlink.backend.auth.application.dto.LoginCommand;

public interface LoginUseCase {

  AuthResult handle(LoginCommand cmd);
}
