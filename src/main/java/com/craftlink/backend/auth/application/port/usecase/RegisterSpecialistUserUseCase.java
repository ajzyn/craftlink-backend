package com.craftlink.backend.auth.application.port.usecase;

import com.craftlink.backend.auth.application.dto.RegisterUserCommand;

public interface RegisterSpecialistUserUseCase {

  void handle(RegisterUserCommand cmd);
}
