package com.craftlink.backend.auth.application.port.in.command.register;

public interface RegisterSpecialistUserUseCase {

  void handle(RegisterUserCommand cmd);
}
