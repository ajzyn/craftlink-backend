package com.craftlink.backend.auth.application.port.in.command.register;

public interface RegisterUserUseCase {

  void handle(RegisterUserCommand cmd);
}
