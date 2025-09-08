package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.application.dto.RegisterUserCommand;

public interface RegisterSpecialistUserUseCase {

  void registerSpecialistUser(RegisterUserCommand cmd);
}
