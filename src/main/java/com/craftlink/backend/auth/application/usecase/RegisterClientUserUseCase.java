package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.application.dto.RegisterUserCommand;

public interface RegisterClientUserUseCase {

  void handle(RegisterUserCommand cmd);
}
